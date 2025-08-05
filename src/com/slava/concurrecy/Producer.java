package com.slava.concurrecy;

import java.util.concurrent.BlockingQueue;

public interface Producer {

    public void stop();
    public void setQueue(BlockingQueue<LogMessage> queue);
}
