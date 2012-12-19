package com.esofthead.mycollab.module.tracker.domain.criteria;

import java.util.Date;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class BugSearchCriteria extends SearchCriteria {
	private StringSearchField assignuser;

	private StringSearchField involeduser;

	private StringSearchField loguser;

	private NumberSearchField projectid;

	private DateSearchField postedDateFrom;

	private DateSearchField postedDateTo;
	
	private DateSearchField updatedDateFrom;
	
	private DateSearchField updatedDateTo;
	
	private DateSearchField dueDateFrom;
	
	private DateSearchField dueDateTo;
	
	private DateSearchField resolvedDateFrom;
	
	private DateSearchField resolvedDateTo;

	private StringSearchField summary;

	private StringSearchField detail;

	private StringSearchField environment;

	private SetSearchField<Integer> resolutions;

	private SetSearchField<Integer> componentids;

	private SetSearchField<Integer> affectedversionids;

	private SetSearchField<Integer> fixedversionids;
	
	private SetSearchField<Integer> versionids;

	private SetSearchField<Integer> priorities;

	private Integer[] severities;

	private Integer[] statuses;
}
