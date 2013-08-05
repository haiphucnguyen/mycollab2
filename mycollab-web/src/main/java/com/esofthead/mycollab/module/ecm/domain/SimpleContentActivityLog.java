package com.esofthead.mycollab.module.ecm.domain;

public class SimpleContentActivityLog extends ContentActivityLog {
	private static final long serialVersionUID = 1L;

	private String userFullName;
	private String userAvatarId;

	private ContentActivityLogAction logAction;

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserAvatarId() {
		return userAvatarId;
	}

	public void setUserAvatarId(String userAvatarId) {
		this.userAvatarId = userAvatarId;
	}

	public ContentActivityLogAction getLogAction() {
		if (logAction == null) {
			logAction = ContentActivityLogAction.fromString(this
					.getActiondesc());
		}

		return logAction;
	}
}
