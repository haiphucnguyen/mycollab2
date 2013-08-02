package com.esofthead.mycollab.module.ecm.domain.criteria;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class ContentActivityLogSearchCriteria extends SearchCriteria {
	private StringSearchField createdUser;
	
	private StringSearchField fromPath;

	public StringSearchField getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(StringSearchField createdUser) {
		this.createdUser = createdUser;
	}

	public StringSearchField getFromPath() {
		return fromPath;
	}

	public void setFromPath(StringSearchField fromPath) {
		this.fromPath = fromPath;
	}
}
