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
package com.esofthead.mycollab.common.domain;

import java.util.List;

public class SimpleActivityStream extends ActivityStream {

	private static final long serialVersionUID = 1L;

	private String createdUserAvatarId;
    private String createdUserFullName;
	private List<SimpleComment> comments;
	private SimpleAuditLog assoAuditLog;

	public List<SimpleComment> getComments() {
		return comments;
	}

	public void setComments(List<SimpleComment> comments) {
		this.comments = comments;
	}

	public String getCreatedUserAvatarId() {
		return createdUserAvatarId;
	}

	public void setCreatedUserAvatarId(String createdUserAvatarId) {
		this.createdUserAvatarId = createdUserAvatarId;
	}

	public String getCreatedUserFullName() {
		return createdUserFullName;
	}

	public void setCreatedUserFullName(String createdUserFullName) {
		this.createdUserFullName = createdUserFullName;
	}

	public SimpleAuditLog getAssoAuditLog() {
		return assoAuditLog;
	}

	public void setAssoAuditLog(SimpleAuditLog assoAuditLog) {
		this.assoAuditLog = assoAuditLog;
	}
}
