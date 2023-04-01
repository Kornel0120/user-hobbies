package com.example.demo.repository;

import com.example.demo.entity.HobbyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<HobbyEntity, Long> {
}
