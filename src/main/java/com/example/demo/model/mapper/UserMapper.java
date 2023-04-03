package com.example.demo.model.mapper;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.User;

public class UserMapper {
    public static User userEntityToUser(UserEntity userEntity) {
        return new User(
            userEntity.getUserId(),
            userEntity.getFirstName(),
            userEntity.getLastName(),
            userEntity.getAge(),
            userEntity.getGender());
    }
    public static UserEntity userToUserEntity(User user) {
        return new UserEntity(
            user.getUserId(),
            user.getFirstName(),
            user.getLastName(),
            user.getAge(),
            user.getGender());
    }
}
