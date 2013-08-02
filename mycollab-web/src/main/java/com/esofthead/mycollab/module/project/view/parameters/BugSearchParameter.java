package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;

public class BugSearchParameter {
	private BugSearchCriteria searchCriteria;

	private String screenTitle;
	
	public BugSearchParameter(String screenTitle, BugSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		this.screenTitle = screenTitle;
	}

	public BugSearchCriteria getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(BugSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public String getScreenTitle() {
		return screenTitle;
	}

	public void setScreenTitle(String screenTitle) {
		this.screenTitle = screenTitle;
	}
}
