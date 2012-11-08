package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;

public class ChangeLogSearchCriteria extends SearchCriteria {
	private NumberSearchField projectId;

	public NumberSearchField getProjectId() {
		return projectId;
	}

	public void setProjectId(NumberSearchField projectId) {
		this.projectId = projectId;
	}
}
