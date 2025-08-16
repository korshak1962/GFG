package com.slava.system_design.memory_pool;

import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SmartMemoryPool {
    private static final int STANDARD_SIZE = 1024 * 1024; // 1MB
    private final BlockingQueue<ByteBuffer> pool;

    public SmartMemoryPool(int poolSize) {
        this.pool = new LinkedBlockingQueue<>(poolSize);
        // Pre-allocate
        for (int i = 0; i < poolSize; i++) {
            pool.offer(ByteBuffer.allocateDirect(STANDARD_SIZE));
        }
    }

    public ByteBuffer allocate(int size) {
        if (size <= STANDARD_SIZE) {
            ByteBuffer buffer = pool.poll(); // Non-blocking
            if (buffer != null) {
                buffer.clear(); // Reset position/limit
                buffer.limit(size); // Set correct limit
                return buffer;
            }
        }
        // Fallback to heap
        return ByteBuffer.allocateDirect(size);
    }

    public void deallocate(ByteBuffer buffer) {
        if (buffer.capacity() == STANDARD_SIZE) {
            buffer.clear();
            pool.offer(buffer); // Return to pool if it doesn't exceed capacity
        }
        // Otherwise, let GC handle it
    }
}
