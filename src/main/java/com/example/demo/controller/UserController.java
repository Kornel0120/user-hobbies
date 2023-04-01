package com.example.demo.controller;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Hobby;
import com.example.demo.model.User;
import com.example.demo.model.UserHobby;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.postUser(user);
            return ResponseEntity.ok("User " + newUser.getFirstName() + " " + newUser.getLastName() + " was registered successfully!");
        } catch (AlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/hobbies")
    public ResponseEntity<List<Hobby>> getHobbies() {
        return ResponseEntity.ok(userService.getHobbies());
    }

    @GetMapping("/{userId}/hobbies")
    public ResponseEntity<List<UserHobby>> getHobbiesForASpecificUser(@PathVariable long userId) {
        try {
            List<UserHobby> hobbies = userService.getHobbiesByUserId(userId);
            return ResponseEntity.ok(hobbies);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
