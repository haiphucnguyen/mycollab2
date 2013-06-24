package com.esofthead.mycollab.module.project.domain;

public class FollowingTicket {

	private int projectId;

	private String projectName;

	private String type;

	private String summary;

	private String assignUser;

	private String assignUserAvatarId;

	private String assignUserFullName;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAssignUser() {
		return assignUser;
	}

	public void setAssignUser(String assignUser) {
		this.assignUser = assignUser;
	}

	public String getAssignUserFullName() {
		return assignUserFullName;
	}

	public void setAssignUserFullName(String assignUserFullName) {
		this.assignUserFullName = assignUserFullName;
	}

	public String getAssignUserAvatarId() {
		return assignUserAvatarId;
	}

	public void setAssignUserAvatarId(String assignUserAvatarId) {
		this.assignUserAvatarId = assignUserAvatarId;
	}
}
