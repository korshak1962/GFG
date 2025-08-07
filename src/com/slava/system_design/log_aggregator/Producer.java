package com.slava.system_design.log_aggregator;

import java.util.concurrent.BlockingQueue;

public interface Producer {

    public void stop();
    public void setQueue(BlockingQueue<LogMessage> queue);
}
