package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;

public interface CampaignListView extends View {
	public static String VIEW_DEF_ID = "crm-campaign-list";

	void enableActionControls(int numOfSelectedItem);

	void disableActionControls();

	HasSelectableItemHandlers<SimpleCampaign> getSelectableItemHandlers();

	HasSearchHandlers<CampaignSearchCriteria> getSearchHandlers();

	HasSelectionOptionHandlers getOptionSelectionHandlers();

	HasPopupActionHandlers getPopupActionHandlers();

	IPagedBeanTable<CampaignSearchCriteria, SimpleCampaign> getPagedBeanTable();
}
