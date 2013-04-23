package com.esofthead.mycollab.module.project.domain;

public class SimpleItemTimeLogging extends ItemTimeLogging {
	private static final long serialVersionUID = 1L;

	private String logUserFullName;

	public String getLogUserFullName() {
		return logUserFullName;
	}

	public void setLogUserFullName(String logUserFullName) {
		this.logUserFullName = logUserFullName;
	}

}
