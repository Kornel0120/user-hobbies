package com.example.demo.service.interfaces;

import com.example.demo.entity.HobbyEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Hobby;
import com.example.demo.model.User;
import com.example.demo.model.UserHobby;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService {
    Collection<User> getUsers();
    User postUser(User user) throws AlreadyExistsException;
    Collection<Hobby> getHobbies();
    Collection<UserHobby> getHobbiesByUserId(Long userId) throws NotFoundException;
}
