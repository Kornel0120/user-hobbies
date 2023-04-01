package com.example.demo.service.impl;

import com.example.demo.entity.UserEntity;
import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.Hobby;
import com.example.demo.model.User;
import com.example.demo.model.UserHobby;
import com.example.demo.repository.HobbyRepository;
import com.example.demo.repository.UserHobbyRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;
    private final UserHobbyRepository userHobbyRepository;
    private final Mapper mapper;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(mapper::userEntityToUser)
                .collect(Collectors.toList());
    }

    @Override
    public User postUser(User user) throws AlreadyExistsException {
        if(userRepository.existsById(user.getUserId())) {
            throw new AlreadyExistsException("User already exists!");
        }

        UserEntity newUser = userRepository.save(mapper.userToUserEntity(user));

        return mapper.userEntityToUser(newUser);
    }

    @Override
    public List<Hobby> getHobbies() {
        return hobbyRepository.findAll()
                .stream()
                .map(mapper::hobbyEntityToHobby)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserHobby> getHobbiesByUserId(Long userId) throws NotFoundException {
        if(!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found!");
        }
        return userHobbyRepository.getHobbiesByUserUserId(userId)
                .stream()
                .map(mapper::userHobbyEntityToUserHobby)
                .collect(Collectors.toList());
    }
}
