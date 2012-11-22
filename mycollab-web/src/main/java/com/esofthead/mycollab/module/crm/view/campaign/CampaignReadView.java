package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface CampaignReadView extends View {
	HasPreviewFormHandlers<Campaign> getPreviewFormHandlers();

	void displayItem(Campaign campaign);
}
