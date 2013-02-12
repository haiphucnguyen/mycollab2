package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.common.domain.PermissionMap;

public class SimpleProject extends Project {
	private static final long serialVersionUID = 1L;

	private String ownerName;

    private String accountName;

    // 0 if project is belong to user in query string, 1 if project has member
    // has username belong in query string,
    // 2 if undefine
    private int ownerType;
    
    private PermissionMap permissionMap;

    public int getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(int ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

	public PermissionMap getPermissionMap() {
		return permissionMap;
	}

	public void setPermissionMap(PermissionMap permissionMap) {
		this.permissionMap = permissionMap;
	}
}
