package com.example.todoapp.core;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private String name;
    private String id;

    public static User createWithNameAndId(String name, String id) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static User createWithName(String name) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(name);
        return user;
    }
}
