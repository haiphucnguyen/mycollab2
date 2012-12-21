package com.esofthead.mycollab.common.domain;

public class SimpleComment extends Comment {
	private static final long serialVersionUID = 1L;
	
	private String ownerFullName;

	public String getOwnerFullName() {
		return ownerFullName;
	}

	public void setOwnerFullName(String ownerFullName) {
		this.ownerFullName = ownerFullName;
	}

}
