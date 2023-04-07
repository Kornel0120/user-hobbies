package com.example.demo.Queue;

import com.example.demo.DataImportRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class TaskExecutorForQueue implements Runnable {
    private final TaskQueue<Runnable> taskQueue;
    private static final Logger logger = Logger.getLogger(DataImportRunner.class.getName());

    public TaskExecutorForQueue(TaskQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Runnable task = taskQueue.dequeue();
                task.run();
                logger.info("Task executed! Remains: " + taskQueue.size());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}






