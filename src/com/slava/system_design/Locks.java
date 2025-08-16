package com.slava.system_design;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

public class Locks {

    private FileChannel channel;
    public void test() {
        try {
            channel.lock();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //channel.read();

        Lock l = new ReentrantLock();
        l.tryLock(); // like synchronized
        l.unlock();
        Condition condition = l.newCondition();
        try {
            condition.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        condition.signal();

        //=========================================
        ReadWriteLock lrw = new ReentrantReadWriteLock();
        Lock read = lrw.readLock();
        Lock write = lrw.writeLock();

        CountDownLatch latch = new CountDownLatch(4);
        latch.countDown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //==========================
        int maxThreads = 5;
        Semaphore sem = new Semaphore(maxThreads);
        try {
            sem.acquire();
            sem.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
