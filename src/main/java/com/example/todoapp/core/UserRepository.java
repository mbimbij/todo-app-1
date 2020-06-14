package com.example.todoapp.core;

public interface UserRepository {
    boolean exists(String userId);

    void save(User user);
}
