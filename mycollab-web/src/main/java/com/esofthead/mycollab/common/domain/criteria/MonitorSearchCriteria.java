package com.esofthead.mycollab.common.domain.criteria;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public class MonitorSearchCriteria extends SearchCriteria {
	
	private String user;
	
	private int typeId;
	
	private String type;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
