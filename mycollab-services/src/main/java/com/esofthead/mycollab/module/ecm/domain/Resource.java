package com.esofthead.mycollab.module.ecm.domain;

import java.util.Calendar;

import com.esofthead.mycollab.core.MyCollabException;

public class Resource implements Comparable {
	protected String uuid = "";
	protected String createdBy = "";
	protected Calendar created;
	protected String path = "";

	// length is Kilobyte value
	protected Double size = 0d;
	protected String createdUser;
	protected String name;

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

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		if (name == null) {
			int index = path.lastIndexOf("/");
			return path.substring(index + 1);
		} else {
			return name;
		}
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Object arg0) {
		if (!(arg0 instanceof Resource)) {
			throw new MyCollabException(
					"Object compare is not a resource instance, it has type "
							+ arg0.getClass().toString());
		} else {
			Resource res = (Resource) arg0;
			if (this instanceof Folder && res instanceof Content) {
				return -1;
			} else if (this instanceof Folder && res instanceof Folder) {
				if (this.getCreated().getTime()
						.compareTo(res.getCreated().getTime()) > 0) {
					return -1;
				} else if (this.getCreated().getTime()
						.compareTo(res.getCreated().getTime()) < 0) {
					return 1;
				} else {
					if (this.getName().compareTo(res.getName()) > 0) {
						return 1;
					} else
						return -1;
				}
			} else {
				return 1;
			}
		}
	}
}
