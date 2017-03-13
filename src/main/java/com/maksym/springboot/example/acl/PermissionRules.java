package com.maksym.springboot.example.acl;

/**
 * Created by Maksym_Hridin on 9/20/2016.
 */
public class PermissionRules {

    public static final String CREATE_CONDITION = "hasPermission(#user, 'CREATE_UPDATE_ACCESS')";

    private PermissionRules() {
        throw new UnsupportedOperationException();
    }
}
