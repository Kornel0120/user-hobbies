package com.example.demo.Queue;

public class QueueImpl<T> {
    private final T[] elements;
    private final int maxSize;
    private int currentSize;
    private int head;
    private int tail;

    public QueueImpl(int maxSize) {
        this.elements = (T[]) new Object[maxSize];
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.head = 0;
        this.tail = 0;
    }

    public void enqueue(T value) throws RuntimeException {
        if (isFull()) {
            throw new RuntimeException("Queue is full.");
        }
        elements[tail] = value;
        tail = (tail + 1) % maxSize;
        currentSize++;
    }

    public T dequeue() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        }
        T value = elements[head];
        head = (head + 1) % maxSize;
        currentSize--;
        return value;
    }

    public T peek() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        }
        return elements[head];
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int size() {
        return currentSize;
    }
}
