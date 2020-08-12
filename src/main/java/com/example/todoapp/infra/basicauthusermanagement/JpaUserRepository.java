package com.example.todoapp.infra.basicauthusermanagement;

import com.example.todoapp.core.User;
import com.example.todoapp.core.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

    private final JpaUserRepositoryInterface jpaUserRepositoryInterface;

    @Override
    public boolean existsById(String userId) {
        return jpaUserRepositoryInterface.existsById(userId);
    }

    @Override
    public boolean existsByName(String userName) {
        return jpaUserRepositoryInterface.existsByUsername(userName);
    }

    @Override
    public void save(User user) {
        if(!jpaUserRepositoryInterface.existsByUsername(user.getName())){
            jpaUserRepositoryInterface.save(UserJpa.fromUser(user));
        }
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return jpaUserRepositoryInterface.findByUsername(username).map(UserJpa::toUser);
    }
}
