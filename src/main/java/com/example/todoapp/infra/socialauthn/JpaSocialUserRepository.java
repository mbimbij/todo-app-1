package com.example.todoapp.infra.socialauthn;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaSocialUserRepository extends JpaRepository<SocialUserInfo, String> {
    boolean existsByIdAndProviderId(String id, String providerId);
    Optional<SocialUserInfo> findByIdAndProviderId(String id, String providerId);
}
