package com.esofthead.mycollab.module.project.domain;

public class SimpleRisk extends Risk {
	private static final long serialVersionUID = 1L;

	private String risksource;

	private String raisedByUserAvatarId;

	private String raisedByUserFullName;

	private String assignToUserAvatarId;

	private String assignedToUserFullName;

	public String getRaisedByUserFullName() {
		return raisedByUserFullName;
	}

	public void setRaisedByUserFullName(String raisedByUserFullName) {
		this.raisedByUserFullName = raisedByUserFullName;
	}

	public String getAssignedToUserFullName() {
		return assignedToUserFullName;
	}

	public void setAssignedToUserFullName(String assignedToUserFullName) {
		this.assignedToUserFullName = assignedToUserFullName;
	}

	public String getRisksource() {
		return risksource;
	}

	public void setRisksource(String risksource) {
		this.risksource = risksource;
	}

	public String getRaisedByUserAvatarId() {
		return raisedByUserAvatarId;
	}

	public void setRaisedByUserAvatarId(String raisedByUserAvatarId) {
		this.raisedByUserAvatarId = raisedByUserAvatarId;
	}

	public String getAssignToUserAvatarId() {
		return assignToUserAvatarId;
	}

	public void setAssignToUserAvatarId(String assignToUserAvatarId) {
		this.assignToUserAvatarId = assignToUserAvatarId;
	}
}
