package com.example.demo.model.mapper;

import com.example.demo.entity.HobbyEntity;
import com.example.demo.model.Hobby;

public class HobbyMapper {
    public static Hobby hobbyEntityToHobby(HobbyEntity hobbyEntity) {
        return new Hobby(
            hobbyEntity.getHobbyId(),
            hobbyEntity.getName());
    }
    public static HobbyEntity hobbyToHobbyEntity(Hobby hobby) {
        return new HobbyEntity(
            hobby.getHobbyId(),
            hobby.getName());
    }
}
