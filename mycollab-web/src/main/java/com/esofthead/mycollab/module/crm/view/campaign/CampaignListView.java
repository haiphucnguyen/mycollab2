package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPagableHandlers;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface CampaignListView extends View {
	void displayCampaigns(List<SimpleCampaign> campaigns, int currentPage,
			int totalPages);

	void enableActionControls(int numOfSelectedItem);

	void disableActionControls();

	void setPresenter(CampaignListPresenter presenter);

	HasSearchHandlers<CampaignSearchCriteria> getSearchHandlers();

	HasPagableHandlers getPagableHandlers();

	HasSelectionOptionHandlers getOptionSelectionHandlers();

	HasPopupActionHandlers getPopupActionHandlers();

	interface CampaignListPresenter extends Presenter {
		void onItemSelect(SimpleCampaign account);

		void doDefaultSearch();

		void doSearch(CampaignSearchCriteria searchCriteria);
	}
}
