package com.example.todoapp.infra.usermanagement;

import com.example.todoapp.core.UnknownUserException;
import com.example.todoapp.core.User;
import com.example.todoapp.core.UserManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JpaUserManager implements UserManager {
    private final JpaUserRepositoryInterface jpaUserRepository;
    private User loggedInUser;

    @Override
    public User getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @Override
    public void setPassword(String password) {
        log.info("setting password for user {}", loggedInUser.getName());
        jpaUserRepository.findByUsername(loggedInUser.getName())
                .map(userJpa -> {
                    userJpa.setPassword(password);
                    return userJpa;
                })
                .ifPresentOrElse(
                        jpaUserRepository::save,
                        () -> {
                            throw new UnknownUserException(loggedInUser.getName());
                        });
    }
}
