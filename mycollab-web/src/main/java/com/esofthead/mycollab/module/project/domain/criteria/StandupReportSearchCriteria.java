package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateTimeSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class StandupReportSearchCriteria extends SearchCriteria {
	private NumberSearchField projectId;

	private StringSearchField logBy;
	
	private RangeDateTimeSearchField reportDateRange;
	
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

	public RangeDateTimeSearchField getReportDateRange() {
		return reportDateRange;
	}

	public void setReportDateRange(RangeDateTimeSearchField reportDateRange) {
		this.reportDateRange = reportDateRange;
	}

	public DateSearchField getOnDate() {
		return onDate;
	}

	public void setOnDate(DateSearchField onDate) {
		this.onDate = onDate;
	}
}
