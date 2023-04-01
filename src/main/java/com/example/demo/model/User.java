package com.example.demo.model;

import com.example.demo.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
}
