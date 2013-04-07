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

	private int numOpenTasks;

	private int numOpenBugs;

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

	public int getNumOpenTasks() {
		return numOpenTasks;
	}

	public void setNumOpenTasks(int numOpenTasks) {
		this.numOpenTasks = numOpenTasks;
	}

	public int getNumOpenBugs() {
		return numOpenBugs;
	}

	public void setNumOpenBugs(int numOpenBugs) {
		this.numOpenBugs = numOpenBugs;
	}
}
