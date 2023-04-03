package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hobbies")
public class HobbyEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long hobbyId;
   private String name;

   public HobbyEntity(Long hobbyId, String name) {
      this.hobbyId = hobbyId;
      this.name = name;
   }

   public HobbyEntity() {
   }

   public Long getHobbyId() {
      return hobbyId;
   }

   public void setHobbyId(Long hobbyId) {
      this.hobbyId = hobbyId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
