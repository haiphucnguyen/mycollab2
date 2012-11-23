package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPagableHandlers;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface OpportunityListView extends View {

	void displayOpportunitys(List<SimpleOpportunity> opportunitys, int currentPage,
			int totalPages);

	void enableActionControls(int numOfSelectedItem);

	void disableActionControls();

	void setPresenter(OpportunityListPresenter presenter);

	HasSearchHandlers<OpportunitySearchCriteria> getSearchHandlers();

	HasPagableHandlers getPagableHandlers();

	HasSelectionOptionHandlers getOptionSelectionHandlers();

	HasPopupActionHandlers getPopupActionHandlers();

	interface OpportunityListPresenter extends Presenter {
		void onItemSelect(SimpleOpportunity opportunity);
		
		void doDefaultSearch();

		void doSearch(OpportunitySearchCriteria searchCriteria);
	}

}
