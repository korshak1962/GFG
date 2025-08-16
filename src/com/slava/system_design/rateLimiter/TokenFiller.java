package com.slava.system_design.rateLimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class TokenFiller {

    private final ConcurrentHashMap<String, AtomicLong> userToTokens;
    final public ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    TokensProvider tokensProvider;


    public TokenFiller(ConcurrentHashMap<String, AtomicLong> userToTokens) {
        this.userToTokens = userToTokens;
    }

    public void sendAlert() {
    }

    public void fillTokensByTmeFrame(TimeUnit timeUnit) {
        scheduler.scheduleAtFixedRate(() -> {
            Thread.currentThread().setDaemon(true);
            if (Thread.interrupted()) return;
            try {
                fillTokens(tokensProvider.getTokensForTimeFrame(timeUnit));
            } catch (Exception e) {
                sendAlert();
            }

        }, 0, 1, timeUnit);
    }

    public void fillTokens(HashMap<String, Integer> sourceMap) {
        for (Map.Entry<String, Integer> entrySource : sourceMap.entrySet()) {
            Integer newTokens = entrySource.getValue();
            userToTokens.computeIfPresent(entrySource.getKey(), (k, v) -> updateCounter(v, newTokens));
            userToTokens.computeIfAbsent(entrySource.getKey(), (k) -> updateCounter(new AtomicLong(), newTokens));
        }
    }

    private static AtomicLong updateCounter(AtomicLong v, Integer newTokens) {
        v.updateAndGet(value -> {
            int counter = (int) (value >>> 32);
            int pending = (int) value;
            counter += newTokens;
            long whole = (long) counter << 32 | pending;
            return whole;
        });
        return v;
    }

}
