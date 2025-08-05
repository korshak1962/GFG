package com.slava.concurrecy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


public class MessageConsumer implements Runnable {
    private final BlockingQueue<LogMessage> queue;
    private FileWriter logWriter;
    public volatile boolean shutdownRequested = false;
    private final BlockingDeque<Long> lastSuccessWrites = new LinkedBlockingDeque<>(List.of(System.currentTimeMillis()));
    private final int lastSuccessWritesSize = 1000;
    private final BlockingDeque<LogMessage> deadLetterQueue = new LinkedBlockingDeque<>();
    private final BlockingDeque<Exception> exceptions = new LinkedBlockingDeque<>();
    private FileWriterProvider fileWriterProvider;


    public MessageConsumer(FileWriterProvider FileWriterProvider, BlockingQueue<LogMessage> queue) {
        this.fileWriterProvider = fileWriterProvider;
        this.logWriter = fileWriterProvider.getFileWriter();
        this.queue = queue;
    }

    public int getMetric_QueueSize() {
        return queue.size();
    }

    public float getMetric_SpeedWrite() {
        return ((float) lastSuccessWrites.size()) / (lastSuccessWrites.getFirst() - lastSuccessWrites.getLast());
    }

    public BlockingDeque<Exception> getExceptions() {
        return exceptions;
    }



    @Override
    public void run() {
        LogMessage logMessage = null;
        while (!shutdownRequested || !queue.isEmpty()) {
            if (Thread.interrupted()) {
                shutdownRequested = true;
            }
            try {
                logMessage = queue.take();
            } catch (InterruptedException e) {
                shutdownRequested = true;
            }
            writeToDisc(logMessage);
        }
        try {
            logWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    int attempts = 0;

    private void writeToDisc(LogMessage logMessage) {
        try {
            logWriter.write(logMessage.toString());
            attempts = 0;
            lastSuccessWrites.add(System.currentTimeMillis());
            while (lastSuccessWrites.size() > lastSuccessWritesSize) {
                lastSuccessWrites.removeFirst();
            }
        } catch (IOException e) {
            attempts++;
            if (attempts > 3) {
                exceptions.add(e);
                FileWriter prevLogWriter = logWriter;
                logWriter = fileWriterProvider.getFileWriter();
                try {
                    logWriter.write(logMessage.toString());
                } catch (IOException ex) {
                    exceptions.add(e);
                    deadLetterQueue.add(logMessage);
                    logWriter = prevLogWriter;
                }
            } else {
                writeToDisc(logMessage);
            }
        }
    }
}
