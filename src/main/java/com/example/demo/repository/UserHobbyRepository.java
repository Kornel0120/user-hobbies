package com.example.demo.repository;

import com.example.demo.entity.UserHobbyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserHobbyRepository extends JpaRepository<UserHobbyEntity, Long> {
    Collection<UserHobbyEntity> getHobbiesByUserUserId(long userId);
}
