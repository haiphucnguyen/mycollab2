package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.Arrays;
import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.module.crm.domain.CampaignLead;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class CampaignAddPresenter extends CrmGenericPresenter<CampaignAddView> {

	private static final long serialVersionUID = 1L;

	public CampaignAddPresenter() {
		super(CampaignAddView.class);
		bind();
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<CampaignWithBLOBs>() {
					@Override
					public void onSave(final CampaignWithBLOBs campaign) {
						saveCampaign(campaign);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new CampaignEvent.GotoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new CampaignEvent.GotoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(final CampaignWithBLOBs campaign) {
						saveCampaign(campaign);
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoAdd(this, null));
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canWrite(RolePermissionCollections.CRM_CAMPAIGN)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_CAMPAIGNS_HEADER));

			CampaignWithBLOBs campaign = null;
			if (data.getParams() instanceof SimpleCampaign) {
				campaign = (SimpleCampaign) data.getParams();
			} else if (data.getParams() instanceof Integer) {
				CampaignService campaignService = AppContext
						.getSpringBean(CampaignService.class);
				campaign = (CampaignWithBLOBs) campaignService
						.findByPrimaryKey((Integer) data.getParams());
				if (campaign == null) {
					AppContext
							.getApplication()
							.getMainWindow()
							.showNotification(
									LocalizationHelper
											.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
									LocalizationHelper
											.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
					return;
				}
			}

			super.onGo(container, data);
			view.editItem(campaign);

			if (campaign.getId() == null) {
				AppContext.addFragment("crm/campaign/add", "Add Campaign");
			} else {
				AppContext.addFragment(
						"crm/campaign/edit/"
								+ UrlEncodeDecoder.encode(campaign.getId()),
						"Edit Campaign: " + campaign.getCampaignname());
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	public void saveCampaign(CampaignWithBLOBs campaign) {
		CampaignService campaignService = AppContext
				.getSpringBean(CampaignService.class);

		campaign.setSaccountid(AppContext.getAccountId());
		if (campaign.getId() == null) {
			campaignService.saveWithSession(campaign, AppContext.getUsername());

			if (campaign.getExtraData() != null
					&& campaign.getExtraData() instanceof SimpleLead) {
				CampaignLead associateLead = new CampaignLead();
				associateLead.setCampaignid(campaign.getId());
				associateLead.setLeadid(((SimpleLead) campaign.getExtraData())
						.getId());
				associateLead.setCreatedtime(new GregorianCalendar().getTime());

				campaignService.saveCampaignLeadRelationship(Arrays
						.asList(associateLead));
			}
		} else {
			campaignService.updateWithSession(campaign,
					AppContext.getUsername());
		}

	}
}
