package com.example.todoapp.infra.basicauthusermanagement;

import com.example.todoapp.core.UnknownUserException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {
    private JpaUserRepositoryInterface repositoryJpa;

    public MyUserDetailsService(JpaUserRepositoryInterface repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repositoryJpa.findByUsername(username)
                .orElseThrow(() -> new UnknownUserException(username));
    }
}
