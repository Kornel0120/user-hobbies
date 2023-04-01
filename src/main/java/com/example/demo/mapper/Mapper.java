package com.example.demo.mapper;

import com.example.demo.entity.HobbyEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserHobbyEntity;
import com.example.demo.model.Hobby;
import com.example.demo.model.User;
import com.example.demo.model.UserHobby;

@org.mapstruct.Mapper(componentModel = "Spring")
public interface Mapper {
    Hobby hobbyEntityToHobby(HobbyEntity hobbyEntity);
    HobbyEntity hobbyToHobbyEntity(Hobby hobby);
    UserHobby userHobbyEntityToUserHobby(UserHobbyEntity userHobbyEntity);
    UserHobbyEntity userHobbyToUserHobbyEntity(UserHobby userHobby);
    User userEntityToUser(UserEntity userEntity);
    UserEntity userToUserEntity(User user);
}
