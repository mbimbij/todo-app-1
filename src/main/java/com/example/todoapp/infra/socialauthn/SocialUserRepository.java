package com.example.todoapp.infra.socialauthn;

import com.example.todoapp.core.User;
import com.example.todoapp.infra.basicauthusermanagement.JpaUserRepositoryInterface;
import com.example.todoapp.infra.basicauthusermanagement.UserJpa;

import java.util.Optional;

public class SocialUserRepository {
    private JpaSocialUserRepository jpaSocialUserRepository;
    private JpaUserRepositoryInterface userRepository;

    public SocialUserRepository(JpaSocialUserRepository jpaSocialUserRepository, JpaUserRepositoryInterface userRepository) {
        this.jpaSocialUserRepository = jpaSocialUserRepository;
        this.userRepository = userRepository;
    }

    public Optional<SocialUserInfo> get(String id, String providerId){
        return jpaSocialUserRepository.findByIdAndProviderId(id, providerId);
    }

    public SocialUserInfo save(SocialUserInfo userInfo) {
        userRepository.save(userInfo.getUserJpa());
        return jpaSocialUserRepository.save(userInfo);
    }

    public boolean exists(String id, String providerId) {
        return jpaSocialUserRepository.existsByIdAndProviderId(id, providerId);
    }
}
