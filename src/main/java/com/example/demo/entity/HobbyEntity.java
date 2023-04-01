package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@Getter
@Setter
@Table(name = "hobbies")
@NoArgsConstructor
@AllArgsConstructor
public class HobbyEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long hobbyId;
   private String name;
}
