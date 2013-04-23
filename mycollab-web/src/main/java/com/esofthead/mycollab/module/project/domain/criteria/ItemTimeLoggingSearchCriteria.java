package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;

public class ItemTimeLoggingSearchCriteria extends SearchCriteria {
	private NumberSearchField projectId;

	private SetSearchField<String> logUsers;

	private RangeDateSearchField rangeDate;

	public NumberSearchField getProjectId() {
		return projectId;
	}

	public void setProjectId(NumberSearchField projectId) {
		this.projectId = projectId;
	}

	public SetSearchField<String> getLogUsers() {
		return logUsers;
	}

	public void setLogUsers(SetSearchField<String> logUsers) {
		this.logUsers = logUsers;
	}

	public RangeDateSearchField getRangeDate() {
		return rangeDate;
	}

	public void setRangeDate(RangeDateSearchField rangeDate) {
		this.rangeDate = rangeDate;
	}
}
