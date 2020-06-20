package com.example.todoapp.core;

import java.util.Optional;

public interface UserRepository {
    boolean existsById(String userId);

    boolean existsByName(String userName);

    void save(User user);

    Optional<User> getByUsername(String username);
}
