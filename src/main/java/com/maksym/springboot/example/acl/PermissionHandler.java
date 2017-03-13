package com.maksym.springboot.example.acl;

import org.springframework.security.core.Authentication;

/**
 * Interface provides method which helps check permissions.
 *
 */
public interface PermissionHandler {
    /**
     * Has permission.
     *
     * @param authentication the authentication
     * @param domain         the domain
     * @return the boolean
     */
    boolean hasPermission(Authentication authentication, Object domain);
}
