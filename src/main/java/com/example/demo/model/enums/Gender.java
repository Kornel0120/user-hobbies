package com.example.demo.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("male"),
    FEMALE("female");
    private final String label;
    Gender(String label) {
        this.label = label;
    }
    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static Gender of(String value) throws Exception {
        for (Gender gender : values()) {
            if (gender.label.equals(value) || gender.name().equals(value)) {
                return gender;
            }
        }
        throw new Exception("massage");
    }

}
