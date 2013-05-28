package com.esofthead.mycollab.module.ecm.domain;

import java.util.Calendar;

public class Resource {
	protected String createdBy = "";
	protected Calendar created;
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Calendar getCreated() {
		return created;
	}
	public void setCreated(Calendar created) {
		this.created = created;
	}
}
