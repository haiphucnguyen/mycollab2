package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ListView;

public interface RiskListView extends ListView<RiskSearchCriteria, SimpleRisk> {

	public static final String VIEW_DEF_ID = "project-risk-list";
}
