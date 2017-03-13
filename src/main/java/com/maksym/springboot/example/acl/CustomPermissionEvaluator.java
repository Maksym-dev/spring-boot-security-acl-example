package com.maksym.springboot.example.acl;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Class helps to determine whether a user has a permission or permissions for a given domain object.
 *
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {
    private Map<String, List<PermissionHandler>> handlerMap;
    private PermissionEvaluator permissionEvaluator;

    /**
     * Instantiates a new CustomPermissionEvaluator.
     *
     * @param permissionEvaluator the permission evaluator
     * @param handlerMap          the handler map
     */
    public CustomPermissionEvaluator(PermissionEvaluator permissionEvaluator, Map<String, List<PermissionHandler>> handlerMap) {
        this.handlerMap = handlerMap;
        this.permissionEvaluator = permissionEvaluator;
    }

    /**
     * Method for evaluating a permission.
     *
     * @param authentication represents the user in question. Should not be null.
     * @param domainObject   the domain object for which permissions should be checked. May be null in which case implementations should return false,
     *                       as the null condition can be checked explicitly in the expression.
     * @param permission     a representation of the permission object as supplied by the expression system. Not null.
     * @return true if the permission is granted, false otherwise
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object domainObject, Object permission) {
        List<PermissionHandler> handlerList = handlerMap.get(permission.toString());
        if (handlerList != null) {
            return checkCustomHandlersPermission(handlerList, authentication, domainObject);
        }
        return permissionEvaluator.hasPermission(authentication, domainObject, permission);
    }

    /**
     * Alternative method for evaluating a permission where only the identifier of the target object is available,
     * rather than the target instance itself.
     *
     * @param authentication   represents the user in question. Should not be null.
     * @param domainObjectId   the identifier for the object instance (usually a Long)
     * @param domainObjectType a String representing the target's type (usually a Java classname). Not null.
     * @param permission       a representation of the permission object as supplied by the expression system. Not null.
     * @return true if the permission is granted, false otherwise.
     */
    @Override
    public boolean hasPermission(Authentication authentication, Serializable domainObjectId, String domainObjectType, Object permission) {
        return permissionEvaluator.hasPermission(authentication, domainObjectId, domainObjectType, permission);
    }

    private boolean checkCustomHandlersPermission(List<PermissionHandler> handlerList, Authentication authentication, Object domainObject) {
        for (PermissionHandler handler : handlerList) {
            if (handler.hasPermission(authentication, domainObject)) {
                return true;
            }
        }
        return false;
    }
}
