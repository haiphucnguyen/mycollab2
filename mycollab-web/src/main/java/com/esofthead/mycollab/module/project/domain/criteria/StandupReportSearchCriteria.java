package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class StandupReportSearchCriteria extends SearchCriteria {
	private NumberSearchField projectId;

	private StringSearchField logBy;

	private RangeDateSearchField reportDateRange;

	private DateSearchField onDate;

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

	public RangeDateSearchField getReportDateRange() {
		return reportDateRange;
	}

	public void setReportDateRange(RangeDateSearchField reportDateRange) {
		this.reportDateRange = reportDateRange;
	}

	public DateSearchField getOnDate() {
		return onDate;
	}

	public void setOnDate(DateSearchField onDate) {
		this.onDate = onDate;
	}
}
