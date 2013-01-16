/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.module.user.PermissionFlag;
import java.util.HashMap;

/**
 *
 * @author haiphucnguyen
 */
public class PermissionMap extends HashMap<String, Integer> {

    public void addPath(String permissionItem, Integer value) {
        this.put(permissionItem, value);
    }

    public Integer getPermissionFlag(String permissionItem) {
        Object value = this.get(permissionItem);
        if (value == null) {
            return PermissionFlag.NO_ACCESS;
        }

        return (Integer) value;
    }

    public boolean canRead(String permissionItem) {
        Object value = this.get(permissionItem);
        if (value == null) {
            return false;
        } else {
            return PermissionFlag.canRead((Integer) value);
        }
    }

    public boolean canWrite(String permissionItem) {
        Object value = this.get(permissionItem);
        if (value == null) {
            return false;
        } else {
            return PermissionFlag.canWrite((Integer) value);
        }
    }

    public boolean canAccess(String permissionItem) {
        Object value = this.get(permissionItem);
        if (value == null) {
            return false;
        } else {
            return PermissionFlag.canAccess((Integer) value);
        }
    }
}
