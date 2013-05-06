package com.esofthead.mycollab.module.tracker.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;

public class BugRelatedSearchCriteria  extends SearchCriteria {
	
	private NumberSearchField bugId;
	
	public NumberSearchField getBugId() {
		return bugId;
	}

	public void setBugId(NumberSearchField bugId) {
		this.bugId = bugId;
	}

}
