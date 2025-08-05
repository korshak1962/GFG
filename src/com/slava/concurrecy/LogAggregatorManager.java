package com.slava.concurrecy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;

public class LogAggregatorManager {
    private BlockingQueue<LogMessage> queue;
    private MessageConsumer messageConsumer;
    private FileWriterProvider fileWriterProvider;
    private List<Producer> producers = new ArrayList<>();

    // Your monitoring components
    private HealthChecker healthChecker;
    private MetricsConsumer metricsConsumer;

    int maxQueueLength;

    public LogAggregatorManager(int maxQueueLength) {
        this.maxQueueLength = maxQueueLength;
    }

    public void start() {
        queue = new LinkedBlockingDeque<>();
        MessageConsumer messageConsumer = new MessageConsumer(fileWriterProvider, queue);
        CompletableFuture future = CompletableFuture.runAsync(messageConsumer);
        healthChecker = new HealthChecker(messageConsumer, maxQueueLength);
        healthChecker.checkHealth();
        metricsConsumer = new MetricsConsumer(messageConsumer);
        metricsConsumer.monitor();
    }

    public void stop() {
        producers.stream().forEach(p -> p.stop());
        messageConsumer.shutdownRequested = true;
        healthChecker.stop();
        metricsConsumer.stop();
    }

    public BlockingQueue<LogMessage> getQueue() {
        return queue;
    }

    public boolean registerProducer(Producer producer) {
        producer.setQueue(queue);
        return producers.add(producer);
    }
}
