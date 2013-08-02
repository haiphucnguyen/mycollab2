package com.esofthead.mycollab.module.tracker.domain.criteria;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public class QuerySearchCriteria extends SearchCriteria {
	private String owner;
	
	private int projectid;

	private Integer[] sharetypes;
	
	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Integer[] getSharetypes() {
		return sharetypes;
	}

	public void setSharetypes(Integer[] sharetypes) {
		this.sharetypes = sharetypes;
	}
}
