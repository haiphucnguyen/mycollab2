package com.esofthead.mycollab.module.project.domain;

public class SimpleChangeLog extends ChangeLog {
	private static final long serialVersionUID = 1L;
	
	private String fullPostedUserName;

	public String getFullPostedUserName() {
		return fullPostedUserName;
	}

	public void setFullPostedUserName(String fullPostedUserName) {
		this.fullPostedUserName = fullPostedUserName;
	}
}
