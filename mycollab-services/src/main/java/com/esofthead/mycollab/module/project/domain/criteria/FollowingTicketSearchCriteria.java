package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class FollowingTicketSearchCriteria extends MonitorSearchCriteria {
	private static final long serialVersionUID = 1L;

	
	private StringSearchField summary;


	public StringSearchField getSummary() {
		return summary;
	}


	public void setSummary(StringSearchField summary) {
		this.summary = summary;
	}
}
