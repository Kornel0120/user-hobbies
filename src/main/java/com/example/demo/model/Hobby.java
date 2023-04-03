package com.example.demo.model;

public class Hobby {
    private Long hobbyId;
    private String name;

    public Hobby(Long hobbyId, String name) {
        this.hobbyId = hobbyId;
        this.name = name;
    }

    public Hobby() {
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
