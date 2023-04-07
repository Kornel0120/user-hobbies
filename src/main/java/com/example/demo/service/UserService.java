package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Hobby;
import com.example.demo.model.User;
import com.example.demo.model.UserHobby;
import com.example.demo.model.mapper.HobbyMapper;
import com.example.demo.model.mapper.UserHobbyMapper;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.repository.HobbyRepository;
import com.example.demo.repository.UserHobbyRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;
    private final UserHobbyRepository userHobbyRepository;

    public UserService(UserRepository userRepository, HobbyRepository hobbyRepository, UserHobbyRepository userHobbyRepository) {
        this.userRepository = userRepository;
        this.hobbyRepository = hobbyRepository;
        this.userHobbyRepository = userHobbyRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::userEntityToUser)
                .collect(Collectors.toList());
    }

    public User postUser(User user) throws AlreadyExistsException {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(userRepository.existsById(user.getUserId())) {
            throw new AlreadyExistsException("User already exists!");
        }

        final UserEntity newUser = userRepository.save(UserMapper.userToUserEntity(user));

        return UserMapper.userEntityToUser(newUser);
    }

    public List<Hobby> getHobbies() {
        return hobbyRepository.findAll()
                .stream()
                .map(HobbyMapper::hobbyEntityToHobby)
                .collect(Collectors.toList());
    }

    public List<UserHobby> getHobbiesByUserId(Long userId) throws NotFoundException {
        if(!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found!");
        }
        return userHobbyRepository.getHobbiesByUserUserId(userId)
                .stream()
                .map(UserHobbyMapper::userHobbyEntityToUserHobby)
                .collect(Collectors.toList());
    }
}
