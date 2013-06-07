package com.esofthead.mycollab.module.ecm.domain;

import java.util.Calendar;

public class Resource {
	protected String createdBy = "";
	protected Calendar created;
	protected String path = "";
	protected long size = 0;

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

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getName() {
		int index = path.lastIndexOf("/");
		return path.substring(index + 1);
	}
}
