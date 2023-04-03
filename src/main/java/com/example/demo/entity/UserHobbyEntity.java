package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.time.Duration;

@Entity
@Table(name = "user_hobby")
public class UserHobbyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "hobbyId")
    private HobbyEntity hobby;
    private Duration duration;
    private Timestamp lastDone;

    public UserHobbyEntity(Long id, UserEntity user, HobbyEntity hobby, Duration duration, Timestamp lastDone) {
        this.id = id;
        this.user = user;
        this.hobby = hobby;
        this.duration = duration;
        this.lastDone = lastDone;
    }

    public UserHobbyEntity() {
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
