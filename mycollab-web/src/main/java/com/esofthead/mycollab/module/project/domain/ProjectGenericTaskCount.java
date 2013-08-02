package com.esofthead.mycollab.module.project.domain;

public class ProjectGenericTaskCount {

	private String assignUser;

	private String assignUserFullName;

	private int projectId;

	private String projectName;

	private int taskCount;

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

	public int getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}
}
