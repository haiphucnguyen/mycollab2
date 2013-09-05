package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ListView;

public interface LeadListView extends ListView<LeadSearchCriteria, SimpleLead> {

	public static String VIEW_DEF_ID = "crm-lead-list";
}
