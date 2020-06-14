package com.example.todoapp.infra;

import com.example.todoapp.core.User;
import com.example.todoapp.core.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class InMemoryUserRepository implements UserRepository {

    private Set<User> users = new HashSet<>();

    @Override
    public boolean exists(String userId) {
        return users.stream().anyMatch(user -> Objects.equals(userId, user.getId()));
    }

    @Override
    public void save(User user) {
        users.add(user);
    }
}
