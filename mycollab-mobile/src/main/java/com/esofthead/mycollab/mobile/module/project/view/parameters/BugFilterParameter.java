package com.esofthead.mycollab.mobile.module.project.view.parameters;

import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class BugFilterParameter {
	private BugSearchCriteria searchCriteria;

	private String screenTitle;

	public BugFilterParameter(String screenTitle,
			BugSearchCriteria searchCriteria) {
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
