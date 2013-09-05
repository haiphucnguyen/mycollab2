package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ListView;

public interface CampaignListView extends
		ListView<CampaignSearchCriteria, SimpleCampaign> {
	public static String VIEW_DEF_ID = "crm-campaign-list";
}
