package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;

public interface LeadListView extends View {

	public static String VIEW_DEF_ID = "crm-lead-list";
	
    IPagedBeanTable<LeadSearchCriteria, SimpleLead> getPagedBeanTable();

    void enableActionControls(int numOfSelectedItem);

    void disableActionControls();

    HasSelectableItemHandlers<SimpleLead> getSelectableItemHandlers();

    HasSearchHandlers<LeadSearchCriteria> getSearchHandlers();

    HasSelectionOptionHandlers getOptionSelectionHandlers();

    HasPopupActionHandlers getPopupActionHandlers();
}
