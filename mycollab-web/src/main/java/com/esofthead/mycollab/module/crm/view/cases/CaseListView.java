package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ListView;

public interface CaseListView extends ListView<CaseSearchCriteria, SimpleCase> {
	public static final String VIEW_DEF_ID = "crm-case-list";
}
