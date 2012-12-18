package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class CampaignReadPresenter  extends CrmGenericPresenter<CampaignReadView> {
	private static final long serialVersionUID = 1L;

	public CampaignReadPresenter() {
		super(CampaignReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new PreviewFormHandlers<Campaign>() {

					@Override
					public void onEdit(Campaign data) {
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(Campaign data) {
						CampaignService campaignService = AppContext
								.getSpringBean(CampaignService.class);
						campaignService.removeWithSession(data.getId(),
								AppContext.getUsername());
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoList(this, null));
					}

					@Override
					public void onClone(Campaign data) {
						Campaign cloneData = (Campaign) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoList(this, null));
					}
				});
	}
	
	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);
		
		if (data.getParams() instanceof Integer) {
			CampaignService campaignService = AppContext.getSpringBean(CampaignService.class);
			SimpleCampaign campaign = campaignService.findCampaignById((Integer)data.getParams());
			view.previewItem(campaign);
		}
		
	}
}
