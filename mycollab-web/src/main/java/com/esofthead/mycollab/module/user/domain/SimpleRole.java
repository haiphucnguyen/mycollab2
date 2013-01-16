/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 *
 * @author haiphucnguyen
 */
public class SimpleRole extends Role {

    private String permissionVal;
    private PermissionMap permissionMap;

    public String getPermissionVal() {
        return permissionVal;
    }

    public void setPermissionVal(String permissionVal) {
        this.permissionVal = permissionVal;
    }

    public PermissionMap getPermissionMap() {
        if (permissionMap == null) {

            if (permissionVal == null || "".equals(permissionVal)) {
                permissionMap = new PermissionMap();
            } else {
                XStream xstream = new XStream(new StaxDriver());
                permissionMap = (PermissionMap) xstream.fromXML(permissionVal);
            }
        }
        return permissionMap;
    }
}
