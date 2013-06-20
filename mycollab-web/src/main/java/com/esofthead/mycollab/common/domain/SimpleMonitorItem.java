package com.esofthead.mycollab.common.domain;

@SuppressWarnings("serial")
public class SimpleMonitorItem extends MonitorItem{

	private String userAvatarId;
	
	private String userFullname;

	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}

	public String getUserFullname() {
		return userFullname;
	}

	public String getUserAvatarId() {
		return userAvatarId;
	}

	public void setUserAvatarId(String userAvatarId) {
		this.userAvatarId = userAvatarId;
	}
}
