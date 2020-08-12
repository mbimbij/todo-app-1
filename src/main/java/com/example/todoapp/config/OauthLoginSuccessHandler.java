package com.example.todoapp.config;

import com.example.todoapp.infra.socialauthn.SocialUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
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
        String username = user.getAttribute("login");
        String socialUserId = Optional.ofNullable(user.getAttribute("id")).map(Object::toString).orElseThrow(() -> new RuntimeException("github user id is null but shouldn't be"));
        if(!socialUserRepository.exists(socialUserId,"github")){
            socialUserRepository.save(socialUserRepository.create(socialUserId,"github",username));
        }
        httpServletResponse.sendRedirect("/");
    }
}
