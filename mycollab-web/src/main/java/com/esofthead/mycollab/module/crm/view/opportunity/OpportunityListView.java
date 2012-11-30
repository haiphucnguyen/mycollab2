package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;

public interface OpportunityListView extends View {

	IPagedBeanTable<OpportunityService, OpportunitySearchCriteria, SimpleOpportunity> getPagedBeanTable();

	void enableActionControls(int numOfSelectedItem);

	void disableActionControls();

	HasSearchHandlers<OpportunitySearchCriteria> getSearchHandlers();

	HasSelectionOptionHandlers getOptionSelectionHandlers();

	HasPopupActionHandlers getPopupActionHandlers();

	HasSelectableItemHandlers<SimpleOpportunity> getSelectableItemHandlers();

}
