package com.esofthead.mycollab.common.domain;

public class SimpleActivityStream extends ActivityStream {
	private static final long serialVersionUID = 1L;
	
	private String createUserFullName;

	public String getCreateUserFullName() {
		return createUserFullName;
	}

	public void setCreateUserFullName(String createUserFullName) {
		this.createUserFullName = createUserFullName;
	}
}
