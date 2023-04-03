package com.example.demo.model.mapper;

import com.example.demo.entity.UserHobbyEntity;
import com.example.demo.model.UserHobby;

public class UserHobbyMapper {
    public static UserHobby userHobbyEntityToUserHobby(UserHobbyEntity userHobbyEntity) {
        return new UserHobby(
            userHobbyEntity.getId(),
            userHobbyEntity.getUser(),
            userHobbyEntity.getHobby(),
            userHobbyEntity.getDuration(),
            userHobbyEntity.getLastDone());

    }
    public static UserHobbyEntity userHobbyToUserHobbyEntity(UserHobby userHobby) {
        return new UserHobbyEntity(
            userHobby.getId(),
            userHobby.getUser(),
            userHobby.getHobby(),
            userHobby.getDuration(),
            userHobby.getLastDone());
    }
}
