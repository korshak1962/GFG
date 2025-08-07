package com.slava.system_design.log_aggregator;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class MetricsConsumer {

    final MessageConsumer messageConsumer;

    public MetricsConsumer(MessageConsumer messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    ThreadFactory daemonFactory = r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    };
    public final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, daemonFactory);

    public void monitor() {
        scheduler.scheduleAtFixedRate(() -> {
            Thread.currentThread().setDaemon(true);

                try {
                    messageConsumer.getMetric_QueueSize();
                    saveMetrics(messageConsumer.getMetric_QueueSize(), messageConsumer.getMetric_SpeedWrite());
                } catch (Exception e) {
// send alert
                }

        }, 0, 1, TimeUnit.SECONDS);
    }

    public void saveMetrics(int queueSize, float speed
    ) {
        // save metrics
    }

    public void stop(){
        scheduler.shutdown();
    }

}
