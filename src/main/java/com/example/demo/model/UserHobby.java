package com.example.demo.model;

import com.example.demo.entity.HobbyEntity;
import com.example.demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UserHobby {
    private Long id;
    private UserEntity user;
    private HobbyEntity hobby;
    private Duration duration;
    private Timestamp lastDone;
}
