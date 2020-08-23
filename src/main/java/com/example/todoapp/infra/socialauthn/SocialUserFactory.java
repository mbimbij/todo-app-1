package com.example.todoapp.infra.socialauthn;

import com.example.todoapp.core.User;
import com.example.todoapp.infra.basicauthusermanagement.UserJpa;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class SocialUserFactory {
    public static SocialUserInfo create(String providerId, Map<String, Object> attributes){
        String id, username;
        if ("google".equals(providerId)){
            id = (String) attributes.get("sub");
            username = (String) attributes.get("name");
        }else if ("github".equals(providerId)){
            id = String.valueOf(attributes.get("id"));
            username = (String) attributes.get("name");
        } else {
            throw new IllegalArgumentException("unknown providerId : "+providerId);
        }
        return create(id, providerId, username);
    }

    public static SocialUserInfo create(String id, String provider, String username){
        if(StringUtils.isBlank(id)){
            throw new IllegalArgumentException(id+": shouldn't be null");
        }
        if(StringUtils.isBlank(provider)){
            throw new IllegalArgumentException(provider+": shouldn't be null");
        }
        if(StringUtils.isBlank(username)){
            throw new IllegalArgumentException(username+": shouldn't be null");
        }
        UserJpa userJpa = UserJpa.fromUser(User.createWithName(username));
        return new SocialUserInfo(id, provider, userJpa);
    }
}
