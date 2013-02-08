package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;

public interface IBugReportDisplayContainer {
	void displayBugReports();

	void displayBugListWidget(String title, BugSearchCriteria criteria);
}
