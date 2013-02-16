package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class StandupReportSearchCriteria extends SearchCriteria {
	private NumberSearchField projectId;

	private StringSearchField logBy;

	public NumberSearchField getProjectId() {
		return projectId;
	}

	public void setProjectId(NumberSearchField projectId) {
		this.projectId = projectId;
	}

	public StringSearchField getLogBy() {
		return logBy;
	}

	public void setLogBy(StringSearchField logBy) {
		this.logBy = logBy;
	}
}
