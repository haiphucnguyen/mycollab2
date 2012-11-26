package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;

public interface CampaignListView extends View {

	void enableActionControls(int numOfSelectedItem);

	void disableActionControls();
	
	HasSelectableItemHandlers<SimpleCampaign> getSelectableItemHandlers();

	HasSearchHandlers<CampaignSearchCriteria> getSearchHandlers();

	HasSelectionOptionHandlers getOptionSelectionHandlers();

	HasPopupActionHandlers getPopupActionHandlers();
	
	IPagedBeanTable<CampaignService, CampaignSearchCriteria, SimpleCampaign> getPagedBeanTable();

	interface CampaignListPresenter extends Presenter {

		void doDefaultSearch();

		void doSearch(CampaignSearchCriteria searchCriteria);
	}
}
