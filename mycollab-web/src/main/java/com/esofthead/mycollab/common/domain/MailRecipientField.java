package com.esofthead.mycollab.common.domain;

public class MailRecipientField {
	private String email;
	private String name;

	public MailRecipientField(String email, String name) {
		this.email = email;
		this.name = name;
	}
	
	public MailRecipientField(String email) {
		this(email, email);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
