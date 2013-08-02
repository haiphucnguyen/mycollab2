/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.domain;

import java.util.Date;

import com.esofthead.mycollab.common.domain.PermissionMap;

/**
 * 
 * @author haiphucnguyen
 */
public class SimpleProjectMember extends ProjectMember {
	private static final long serialVersionUID = 1L;

	private String memberAvatarId;
	
	private String memberFullName;

	private String roleName;

	private PermissionMap permissionMaps;

	private int numOpenTasks;

	private int numOpenBugs;
	
	private String projectName;
	
	private String email;
	
	private int sAccountId;
	
	private Date lastAccessTime;

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

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getMemberAvatarId() {
		return memberAvatarId;
	}

	public void setMemberAvatarId(String memberAvatarId) {
		this.memberAvatarId = memberAvatarId;
	}

	public int getsAccountId() {
		return sAccountId;
	}

	public void setsAccountId(int sAccountId) {
		this.sAccountId = sAccountId;
	}

	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
}
