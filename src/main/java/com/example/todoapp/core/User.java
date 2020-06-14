package com.example.todoapp.core;

import lombok.Data;

@Data
public class User {
    private String id;

    public static User createWithId(String userId) {
        User user = new User();
        user.setId(userId);
        return user;
    }
}
