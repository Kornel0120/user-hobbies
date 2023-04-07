package com.example.demo.controller;

import com.example.demo.DataImportRunner;
import com.example.demo.Queue.TaskExecutorForQueue;
import com.example.demo.Queue.TaskQueue;
import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Hobby;
import com.example.demo.model.User;
import com.example.demo.model.UserHobby;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private static final Logger logger = Logger.getLogger(DataImportRunner.class.getName());

    private final TaskQueue<Runnable> taskQueue;

    private final TaskExecutorForQueue taskExecutor;

    public UserController(UserService userService, TaskQueue<Runnable> taskQueue, TaskExecutorForQueue taskExecutor) {
        this.userService = userService;
        this.taskQueue = taskQueue;
        this.taskExecutor = taskExecutor;
        startTaskExecutor();
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            final User newUser = userService.postUser(user);
            return ResponseEntity.ok("User " + newUser.getFirstName() + " " + newUser.getLastName() + " was registered successfully!");
        } catch (AlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/queue/postUser")
    public ResponseEntity<String> registerUserWithQueue(@RequestBody User user) {
        String id = taskQueue.enqueue(() -> {
            try {
                final User newUser = userService.postUser(user);
                logger.info("User " + newUser.getFirstName() + " " + newUser.getLastName() + " was registered successfully!");
            } catch (AlreadyExistsException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        });
        return ResponseEntity.accepted().body("Request accepted. Task id: " + id);
    }


    @GetMapping("/hobbies")
    public ResponseEntity<List<Hobby>> getHobbies() {
        return ResponseEntity.ok(userService.getHobbies());
    }

    @GetMapping("/{userId}/hobbies")
    public ResponseEntity<List<UserHobby>> getHobbiesForASpecificUser(@PathVariable long userId) {
        try {
            final List<UserHobby> hobbies = userService.getHobbiesByUserId(userId);
            return ResponseEntity.ok(hobbies);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private void startTaskExecutor() {
        new Thread(taskExecutor).start();
    }

}
