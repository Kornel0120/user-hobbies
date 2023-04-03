package com.example.demo.model;

import com.example.demo.entity.HobbyEntity;
import com.example.demo.entity.UserEntity;

import java.sql.Timestamp;
import java.time.Duration;

public class UserHobby {
    private Long id;
    private UserEntity user;
    private HobbyEntity hobby;
    private Duration duration;
    private Timestamp lastDone;

    public UserHobby(Long id, UserEntity user, HobbyEntity hobby, Duration duration, Timestamp lastDone) {
        this.id = id;
        this.user = user;
        this.hobby = hobby;
        this.duration = duration;
        this.lastDone = lastDone;
    }

    public UserHobby() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public HobbyEntity getHobby() {
        return hobby;
    }

    public void setHobby(HobbyEntity hobby) {
        this.hobby = hobby;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Timestamp getLastDone() {
        return lastDone;
    }

    public void setLastDone(Timestamp lastDone) {
        this.lastDone = lastDone;
    }
}
