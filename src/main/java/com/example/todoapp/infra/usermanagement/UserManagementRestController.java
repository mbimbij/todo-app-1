package com.example.todoapp.infra.usermanagement;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
}
