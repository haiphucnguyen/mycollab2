package com.esofthead.mycollab.common.domain;

@SuppressWarnings("serial")
public class SimpleMonitorItem extends MonitorItem{

	private String userFullname;

	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}

	public String getUserFullname() {
		return userFullname;
	}
	
}
