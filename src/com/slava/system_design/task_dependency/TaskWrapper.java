package com.slava.system_design.task_dependency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class TaskWrapper extends RecursiveTask<Integer> {
    private final Task task;
    private final List<TaskWrapper> tasks = new ArrayList<>();

    public TaskWrapper(Task task) {
        this.task = task;
    }

    @Override
    protected Integer compute() {
        for (Task dep : task.dependencies) {
            TaskWrapper child = new TaskWrapper(dep);
            child.fork();
            tasks.add(child);
        }
        Integer sum = 0;
        for (TaskWrapper child : tasks) {
            sum += child.join();
        }
        return sum;
    }
}
