package com.esofthead.mycollab.common.domain;

import java.util.List;

import com.esofthead.mycollab.module.user.domain.SimpleUser;

public class SimpleRelayEmailNotification extends RelayEmailNotification {
	private static final long serialVersionUID = 1L;

	private String changeByUserFullName;
	
	private List<SimpleUser> notifyUsers;

	public String getChangeByUserFullName() {
		return changeByUserFullName;
	}

	public void setChangeByUserFullName(String changeByUserFullName) {
		this.changeByUserFullName = changeByUserFullName;
	}

	public List<SimpleUser> getNotifyUsers() {
		return notifyUsers;
	}

	public void setNotifyUsers(List<SimpleUser> notifyUsers) {
		this.notifyUsers = notifyUsers;
	}
}
