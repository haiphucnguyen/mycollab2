package com.esofthead.mycollab.module.crm.domain;

import java.util.List;

import com.esofthead.mycollab.module.file.domain.Attachment;

public class SimpleNote extends Note {
	private static final long serialVersionUID = 1L;

	private String contactName;

	private String createUserFullName;

	private List<Attachment> attachments;

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getCreateUserFullName() {
		return createUserFullName;
	}

	public void setCreateUserFullName(String createUserFullName) {
		this.createUserFullName = createUserFullName;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
}
