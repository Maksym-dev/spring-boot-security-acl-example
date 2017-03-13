package com.maksym.springboot.example.acl.handler.admin;

import org.springframework.security.acls.domain.AbstractPermission;
import org.springframework.security.acls.model.Permission;

/**
 * Created by Maksym_Hridin on 2/28/2017.
 */
public class CustomPermission extends AbstractPermission {
    private static final long serialVersionUID = -6496070986732627872L;

    public static final Permission READ = new CustomPermission(1, 'R'); // 1
    public static final Permission CANCEL = new CustomPermission(1 << 1, 'C'); // 2
    public static final Permission ACCEPT = new CustomPermission(1 << 2, 'A'); // 4

    protected CustomPermission(int mask) {
        super(mask);
    }

    protected CustomPermission(int mask, char code) {
        super(mask, code);
    }
}
