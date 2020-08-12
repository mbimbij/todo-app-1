package com.example.todoapp.infra.basicauthusermanagement;

import com.example.todoapp.core.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserJpa implements UserDetails {
    @Id
    private String id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User toUser() {
        return User.createWithNameAndId(username, id);
    }

    public static UserJpa fromUser(User user) {
        return new UserJpa(user.getId(), user.getName(), null);
    }

    public static UserJpa fromUserAndPassword(User user, String password) {
        return new UserJpa(user.getId(), user.getName(), password);
    }
}
