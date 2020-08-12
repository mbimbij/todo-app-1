package com.example.todoapp.infra.basicauthusermanagement;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController
public class UserManagementRestController {
    @GetMapping("/currentUser")
    public String getCurrentUser(Principal principal){
        if(principal == null || StringUtils.isBlank(principal.getName())){
            return "anonymous";
        }else{
            return principal.getName();
        }
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }
}
