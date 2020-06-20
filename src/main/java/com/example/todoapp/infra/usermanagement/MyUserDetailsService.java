package com.example.todoapp.infra.usermanagement;

import com.example.todoapp.core.UnknownUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private JpaUserRepositoryInterface repositoryJpa;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repositoryJpa.findByUsername(username)
                .orElseThrow(() -> new UnknownUserException(username));
    }
}
