package com.esofthead.mycollab.module.project.domain;

public class SimpleProblem extends Problem {
	private static final long serialVersionUID = 1L;

	private String raisedByUserAvatarId;

	private String raisedByUserFullName;

	private String assignUserAvatarId;

	private String assignedUserFullName;

	public String getRaisedByUserAvatarId() {
		return raisedByUserAvatarId;
	}

	public void setRaisedByUserAvatarId(String raisedByUserAvatarId) {
		this.raisedByUserAvatarId = raisedByUserAvatarId;
	}

	public String getAssignUserAvatarId() {
		return assignUserAvatarId;
	}

	public void setAssignUserAvatarId(String assignUserAvatarId) {
		this.assignUserAvatarId = assignUserAvatarId;
	}

	public String getRaisedByUserFullName() {
		return raisedByUserFullName;
	}

	public void setRaisedByUserFullName(String raisedByUserFullName) {
		this.raisedByUserFullName = raisedByUserFullName;
	}

	public String getAssignedUserFullName() {
		return assignedUserFullName;
	}

	public void setAssignedUserFullName(String assignedUserFullName) {
		this.assignedUserFullName = assignedUserFullName;
	}
}
