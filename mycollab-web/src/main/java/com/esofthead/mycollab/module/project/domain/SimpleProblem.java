package com.esofthead.mycollab.module.project.domain;

public class SimpleProblem extends Problem {
	private static final long serialVersionUID = 1L;

	private String raisedByUserFullName;

    private String assignedUserFullName;

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
