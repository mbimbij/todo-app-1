package com.example.todoapp.config;

import com.example.todoapp.infra.socialauthn.SocialUserFactory;
import com.example.todoapp.infra.socialauthn.SocialUserInfo;
import com.example.todoapp.infra.socialauthn.SocialUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class OauthLoginSuccessHandler implements AuthenticationSuccessHandler {

    private SocialUserRepository socialUserRepository;

    public OauthLoginSuccessHandler(SocialUserRepository socialUserRepository) {
        this.socialUserRepository = socialUserRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        this.onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        OAuth2User user = (OAuth2User) authentication.getPrincipal();
        String providerId = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();
        SocialUserInfo socialUserInfo = SocialUserFactory.create(providerId, user.getAttributes());

        if(!socialUserRepository.exists(socialUserInfo.getId(),providerId)){
            socialUserRepository.save(socialUserInfo);
        }
        httpServletResponse.sendRedirect("/");
    }
}
