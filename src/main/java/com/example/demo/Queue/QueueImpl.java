package com.example.demo.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueImpl<T> {
    private final Queue<T> queue;

    public QueueImpl() {
        this.queue = new LinkedList<>();
    }

    public void enqueue(T element) {
        queue.offer(element);
    }

    public  T dequeue() {
        return queue.poll();
    }

    public T peek() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public boolean contains(T element) {
        return queue.contains(element);
    }
}
