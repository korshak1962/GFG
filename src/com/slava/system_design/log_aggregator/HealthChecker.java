package com.slava.system_design.log_aggregator;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class HealthChecker {
    final MessageConsumer messageConsumer;

    public HealthChecker(MessageConsumer messageConsumer, int maxQueueLength) {
        this.messageConsumer = messageConsumer;
    }

    private void sendAlert() {
        // send alert
    }

    ThreadFactory daemonFactory = r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    };
    final public ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, daemonFactory);

    public void checkHealth() {
        // Run every 10 seconds
        scheduler.scheduleAtFixedRate(() -> {
            Thread.currentThread().setDaemon(true);
            if (Thread.interrupted()) return;
            try {
                messageConsumer.getMetric_QueueSize();
            } catch (Exception e) {
                sendAlert();
            }

        }, 0, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduler.shutdown();
    }

}
