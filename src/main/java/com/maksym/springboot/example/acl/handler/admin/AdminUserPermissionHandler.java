package com.maksym.springboot.example.acl.handler.admin;

import com.maksym.springboot.example.acl.PermissionHandler;
import com.maksym.springboot.example.model.User;
import org.springframework.security.core.Authentication;

/**
 * Created by Maksym_Hridin on 2/28/2017.
 */
public class AdminUserPermissionHandler implements PermissionHandler {

    @Override
    public boolean hasPermission(Authentication authentication, Object domain) {
        User user = (User) domain;
        System.out.println(user);
        return true;
    }
}
