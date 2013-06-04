package com.esofthead.mycollab.module.project.domain;

public class SimpleRisk extends Risk {
	private static final long serialVersionUID = 1L;

	private String risksource;

	private String raisedByUserFullName;

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
}
