package com.esofthead.mycollab.module.crm.view.lead;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPagableHandlers;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface LeadListView  extends View {

	void displayLeads(List<SimpleLead> accounts, int currentPage,
			int totalPages);

	void enableActionControls(int numOfSelectedItem);

	void disableActionControls();
	
	HasSelectableItemHandlers<SimpleLead> getSelectableItemHandlers();

	HasSearchHandlers<LeadSearchCriteria> getSearchHandlers();

	HasPagableHandlers getPagableHandlers();

	HasSelectionOptionHandlers getOptionSelectionHandlers();

	HasPopupActionHandlers getPopupActionHandlers();

	interface LeadListPresenter extends Presenter {
		
		void doDefaultSearch();

		void doSearch(LeadSearchCriteria searchCriteria);
	}

}
