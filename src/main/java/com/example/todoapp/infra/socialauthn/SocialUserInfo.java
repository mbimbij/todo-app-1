package com.example.todoapp.infra.socialauthn;

import com.example.todoapp.infra.basicauthusermanagement.UserJpa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SocialUserInfo {
    @Id
    @Column(nullable = false)
    private String id;
    @Column(nullable = false, unique = true)
    private String providerId;
    @OneToOne
    @JoinColumn(name = "fk_user")
    private UserJpa userJpa;

}
