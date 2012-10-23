package com.esofthead.mycollab.module.crm.domain;

public class SimpleNote extends Note {
	private String contactName;
	
	private String assignUserFullName;

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getAssignUserFullName() {
		return assignUserFullName;
	}

	public void setAssignUserFullName(String assignUserFullName) {
		this.assignUserFullName = assignUserFullName;
	}
	
}
