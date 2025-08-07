package com.slava.system_design.rateLimiter;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DistributedRateLimiter {

    private final int THRESHOLD;

    private final ConcurrentHashMap<String, AtomicLong> userToTokens = new ConcurrentHashMap<>();

    public DistributedRateLimiter(int threshold) {
        THRESHOLD = threshold;
    }

    public boolean allowRequest(String userId) {
        AtomicLong countersLong = userToTokens.get(userId);
        if (countersLong == null) return false;
        long newState = countersLong.updateAndGet(packed2Counters -> {
            int counter = (int) (packed2Counters >>> 32);    // Main token counter
            int pending = (int) packed2Counters;             // Pending requests
            pending++;  // Increment pending requests
            // Flush pending to counter when threshold reached
            if (pending >= THRESHOLD) {
                counter -= pending;                 // SUBTRACT pending (consume tokens)
                pending = 0;                        // Reset pending
            }
            long value = (long) counter << 32;
            return value | pending;
        });
        return (int) (newState >>> 32) >= (int) newState;
    }

    public ConcurrentHashMap<String, AtomicLong> getUserToTokens() {
        return userToTokens;
    }
}
