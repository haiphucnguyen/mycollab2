package com.esofthead.mycollab.module.project.domain;

public class SimpleItemTimeLogging extends ItemTimeLogging {
	private static final long serialVersionUID = 1L;

	private String logUserFullName;
	
	private String projectName;
	
	private String projectShortName;
	
	private String summary;

	public String getLogUserFullName() {
		return logUserFullName;
	}

	public void setLogUserFullName(String logUserFullName) {
		this.logUserFullName = logUserFullName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectShortName() {
		return projectShortName;
	}

	public void setProjectShortName(String projectShortName) {
		this.projectShortName = projectShortName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}
