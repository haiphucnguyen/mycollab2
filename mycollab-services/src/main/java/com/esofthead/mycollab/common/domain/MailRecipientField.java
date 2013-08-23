package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class MailRecipientField extends ValuedBean {
	private static final long serialVersionUID = 1L;

	private String email;
	private String name;

	public MailRecipientField(String email, String name) {
		this.email = email;
		if (name != null && name.length() > 0) {
			this.name = name;
		} else {
			this.name = email;
		}
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
