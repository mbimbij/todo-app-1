package com.example.todoapp.infra.basicauthusermanagement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepositoryInterface extends JpaRepository<UserJpa, String> {
    Optional<UserJpa> findByUsername(String username);
    boolean existsByUsername(String username);
}
