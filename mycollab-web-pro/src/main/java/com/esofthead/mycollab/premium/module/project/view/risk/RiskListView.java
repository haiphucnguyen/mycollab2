package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.vaadin.web.ui.IListView;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface RiskListView extends IListView<RiskSearchCriteria, SimpleRisk> {

    String VIEW_DEF_ID = "project-risk-list";
}
