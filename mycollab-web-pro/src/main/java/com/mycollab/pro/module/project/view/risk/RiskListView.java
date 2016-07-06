package com.mycollab.pro.module.project.view.risk;

import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.mycollab.vaadin.web.ui.IListView;
import com.mycollab.vaadin.web.ui.InitializingView;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface RiskListView extends IListView<RiskSearchCriteria, SimpleRisk>, InitializingView {
}
