package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;

public class MessageSearchCriteria extends SearchCriteria {
	private NumberSearchField id;
	
    private NumberSearchField projectid;
    
    private NumberSearchField categoryid;

	public NumberSearchField getId() {
		return id;
	}

	public void setId(NumberSearchField id) {
		this.id = id;
	}

	public NumberSearchField getProjectid() {
		return projectid;
	}

	public void setProjectid(NumberSearchField projectid) {
		this.projectid = projectid;
	}

	public NumberSearchField getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(NumberSearchField categoryid) {
		this.categoryid = categoryid;
	}
}
