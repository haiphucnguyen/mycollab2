package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.web.AppContext;

public class CampaignAddPresenter extends CrmGenericPresenter<CampaignAddView> {
	public CampaignAddPresenter(CampaignAddView view) {
		this.view = view;
		bind();
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<Campaign>() {

					@Override
					public void onSave(final Campaign campaign) {
						saveCampaign(campaign);
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoList(this, null));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoList(this, null));
					}

					@Override
					public void onSaveAndNew(final Campaign campaign) {
						saveCampaign(campaign);
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoAdd(this, null));
					}
				});
	}

	public void saveCampaign(Campaign campaign) {
		CampaignService campaignService = AppContext
				.getSpringBean(CampaignService.class);

		campaign.setSaccountid(AppContext.getAccountId());
		if (campaign.getId() == null) {
			campaignService.saveWithSession(campaign, AppContext.getUsername());
		} else {
			campaignService.updateWithSession(campaign,
					AppContext.getUsername());
		}

	}
}
