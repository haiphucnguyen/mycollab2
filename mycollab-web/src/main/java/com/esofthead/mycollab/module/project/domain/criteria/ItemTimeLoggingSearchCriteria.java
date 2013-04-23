package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class ItemTimeLoggingSearchCriteria extends SearchCriteria {
	private NumberSearchField projectId;

	private SetSearchField<String> logUsers;

	private RangeDateSearchField rangeDate;
	
	private StringSearchField type;
	
	private NumberSearchField typeId;

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

	public void setType(StringSearchField type) {
		this.type = type;
	}

	public StringSearchField getType() {
		return type;
	}

	public void setTypeId(NumberSearchField typeId) {
		this.typeId = typeId;
	}

	public NumberSearchField getTypeId() {
		return typeId;
	}
}
