package com.slava.concurrecy;

import java.io.FileWriter;

public interface FileWriterProvider {
    FileWriter getFileWriter();
}
