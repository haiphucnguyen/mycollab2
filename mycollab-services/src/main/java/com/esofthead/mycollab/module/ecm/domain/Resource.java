/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.ecm.domain;

import java.util.Calendar;

import com.esofthead.mycollab.core.MyCollabException;

public class Resource implements Comparable {
	protected String uuid = "";
	protected String createdBy = "";
	protected Calendar created;
	protected String path = "";

	// length is Kilobyte value
	protected Long size = 0L;
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

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
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
				if (this.getCreated() != null && res.getCreated() != null) {
					return this.getCreated().getTime()
							.compareTo(res.getCreated().getTime());
				} else {
					return this.getName().compareTo(res.getName());
				}
			} else {
				return 1;
			}
		}
	}
}
