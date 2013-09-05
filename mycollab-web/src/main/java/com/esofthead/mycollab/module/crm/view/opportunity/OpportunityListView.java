package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ListView;

public interface OpportunityListView extends
		ListView<OpportunitySearchCriteria, SimpleOpportunity> {
	public static final String VIEW_DEF_ID = "crm-opportunity-list";

}
