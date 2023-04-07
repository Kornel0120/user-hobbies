package com.example.demo.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueImpl<T> {
    private final Queue<T> queue;

    public QueueImpl() {
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueue(T element) {
        queue.offer(element);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
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

    public synchronized boolean contains(T element) {
        return queue.contains(element);
    }
}
