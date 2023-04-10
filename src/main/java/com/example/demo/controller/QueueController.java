package com.example.demo.controller;

import com.example.demo.DataImportRunner;
import com.example.demo.Queue.QueueImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/queue")
public class QueueController {
    public static QueueImpl<String> queue;

    private static final Logger logger = Logger.getLogger(DataImportRunner.class.getName());

    public QueueController() {
        queue = new QueueImpl<>(10);
    }

    @PostMapping("/enqueue/{string}")
    public ResponseEntity<String> enqueue(@PathVariable String string) {
        try {
            queue.enqueue(string);
        } catch (Exception e) {
            return ResponseEntity.ok("Queue is full");
        }
        logger.info("Size: " + queue.size());
        return ResponseEntity.ok("Enqueued: " + string);
    }

    @GetMapping("/dequeue")
    public ResponseEntity<String> dequeue() {
        logger.info("Size: " + queue.size());
        if (queue.isEmpty()) {
            return ResponseEntity.ok("Queue is empty");
        }
        return ResponseEntity.ok("Dequeued: " + queue.dequeue());
    }

    @GetMapping("/peek")
    public ResponseEntity<String> peek() {
        logger.info("Size: " + queue.size());
        try {
            return ResponseEntity.ok("Peeked: " + queue.peek());
        } catch (Exception e) {
            return ResponseEntity.ok("Queue is empty");
        }
    }

    @GetMapping("/isEmpty")
    public ResponseEntity<String> isEmpty() {
        logger.info("Size: " + queue.size());
        if (queue.isEmpty()) {
            return ResponseEntity.ok("Queue is empty");
        }
        return ResponseEntity.ok("Queue is not empty");
    }

    @GetMapping("/size")
    public ResponseEntity<String> size() {
        logger.info("Size: " + queue.size());
        return ResponseEntity.ok("Size: " + queue.size());
    }
}
