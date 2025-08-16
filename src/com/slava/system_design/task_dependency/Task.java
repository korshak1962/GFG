package com.slava.system_design.task_dependency;

import java.util.List;

class Task {
    String taskId;
    List<Task> dependencies;  // Tasks that must complete before this one
    int executionTimeMs;        // Simulated work time

    int getSum() {
        // Simulate work
        try {
            Thread.sleep(executionTimeMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task " + taskId + " completed by " + Thread.currentThread().getName());
        return 1;
    }
}
