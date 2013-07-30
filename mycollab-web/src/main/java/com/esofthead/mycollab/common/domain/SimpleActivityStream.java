package com.esofthead.mycollab.common.domain;

import java.util.List;

public class SimpleActivityStream extends ActivityStream {

	private static final long serialVersionUID = 1L;

	private String ownerAvatarId;
	private String ownerFullName;
	private List<SimpleComment> comments;
	private SimpleAuditLog assoAuditLog;

	public List<SimpleComment> getComments() {
		return comments;
	}

	public void setComments(List<SimpleComment> comments) {
		this.comments = comments;
	}

	public String getOwnerAvatarId() {
		return ownerAvatarId;
	}

	public void setOwnerAvatarId(String createdUserAvatarId) {
		this.ownerAvatarId = createdUserAvatarId;
	}

	public String getOwnerFullName() {
		return ownerFullName;
	}

	public void setOwnerFullName(String createdUserFullName) {
		this.ownerFullName = createdUserFullName;
	}

	public SimpleAuditLog getAssoAuditLog() {
		return assoAuditLog;
	}

	public void setAssoAuditLog(SimpleAuditLog assoAuditLog) {
		this.assoAuditLog = assoAuditLog;
	}
}
