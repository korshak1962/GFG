package com.slava.system_design.log_aggregator;

import java.io.FileWriter;

public interface FileWriterProvider {
    FileWriter getFileWriter();
}
