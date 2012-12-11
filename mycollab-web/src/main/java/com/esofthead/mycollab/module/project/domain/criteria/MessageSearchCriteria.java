package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class MessageSearchCriteria extends SearchCriteria {
	private NumberSearchField id;
	
    private NumberSearchField projectid;
    
    private StringSearchField category;

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

	public StringSearchField getCategory() {
		return category;
	}

	public void setCategory(StringSearchField category) {
		this.category = category;
	}
}
