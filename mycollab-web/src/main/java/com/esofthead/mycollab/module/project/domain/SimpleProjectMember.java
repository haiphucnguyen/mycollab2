/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.common.domain.PermissionMap;

/**
 *
 * @author haiphucnguyen
 */
public class SimpleProjectMember extends ProjectMember {
	private static final long serialVersionUID = 1L;

	private String memberFullName;
    
    private String roleName;
    
    private PermissionMap permissionMaps;

    public String getMemberFullName() {
        return memberFullName;
    }

    public void setMemberFullName(String memberFullName) {
        this.memberFullName = memberFullName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

	public PermissionMap getPermissionMaps() {
		return permissionMaps;
	}

	public void setPermissionMaps(PermissionMap permissionMaps) {
		this.permissionMaps = permissionMaps;
	}
}
