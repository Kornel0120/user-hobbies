package com.example.demo.Queue;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

@Component
public class TaskQueue<T> {
    private final Queue<T> queue;

    private final Queue<String> taskIds;

    public TaskQueue() {
        queue = new LinkedList<>();
        taskIds = new LinkedList<>();
    }

    public synchronized String enqueue(T task) {
        String taskId = UUID.randomUUID().toString();
        queue.offer(task);
        taskIds.offer(taskId);
        notifyAll();
        return taskId;
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        taskIds.poll();
        return queue.poll();
    }

    public synchronized T peek() {
        return queue.peek();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized int size() {
        return queue.size();
    }
}
