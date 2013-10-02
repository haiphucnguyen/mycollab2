package com.esofthead.mycollab.module.crm.view.lead;

import java.util.Arrays;
import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.CampaignLead;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.OpportunityLead;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class LeadAddPresenter extends CrmGenericPresenter<LeadAddView> {

	private static final long serialVersionUID = 1L;

	public LeadAddPresenter() {
		super(LeadAddView.class);
		bind();
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(new EditFormHandler<Lead>() {
			@Override
			public void onSave(final Lead lead) {
				saveLead(lead);
				ViewState viewState = HistoryViewManager.back();
				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new LeadEvent.GotoList(this, null));
				}
			}

			@Override
			public void onCancel() {
				ViewState viewState = HistoryViewManager.back();
				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new LeadEvent.GotoList(this, null));
				}
			}

			@Override
			public void onSaveAndNew(final Lead lead) {
				saveLead(lead);
				EventBus.getInstance().fireEvent(
						new LeadEvent.GotoAdd(this, null));
			}
		});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canWrite(RolePermissionCollections.CRM_LEAD)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_LEADS_HEADER));

			Lead lead = null;

			if (data.getParams() instanceof Lead) {
				lead = (Lead) data.getParams();
			} else if (data.getParams() instanceof Integer) {
				LeadService leadService = ApplicationContextUtil
						.getSpringBean(LeadService.class);
				lead = (Lead) leadService.findByPrimaryKey(
						(Integer) data.getParams(), AppContext.getAccountId());
				if (lead == null) {
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
			view.editItem(lead);

			if (lead.getId() == null) {
				AppContext.addFragment("crm/lead/add", LocalizationHelper
						.getMessage(GenericI18Enum.BROWSER_ADD_ITEM_TITLE,
								"Lead"));
			} else {
				AppContext.addFragment(
						"crm/lead/edit/"
								+ UrlEncodeDecoder.encode(lead.getId()),
						LocalizationHelper.getMessage(
								GenericI18Enum.BROWSER_EDIT_ITEM_TITLE, "Lead",
								lead.getLastname()));
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}

	}

	public void saveLead(Lead lead) {
		LeadService leadService = ApplicationContextUtil.getSpringBean(LeadService.class);

		lead.setSaccountid(AppContext.getAccountId());
		if (lead.getId() == null) {
			leadService.saveWithSession(lead, AppContext.getUsername());

			if (lead.getExtraData() != null
					&& (lead.getExtraData() instanceof SimpleCampaign)) {
				CampaignLead associateLead = new CampaignLead();
				associateLead.setCampaignid(((SimpleCampaign) lead
						.getExtraData()).getId());
				associateLead.setLeadid(lead.getId());
				associateLead.setCreatedtime(new GregorianCalendar().getTime());

				CampaignService campaignService = ApplicationContextUtil
						.getSpringBean(CampaignService.class);
				campaignService
						.saveCampaignLeadRelationship(
								Arrays.asList(associateLead),
								AppContext.getAccountId());
			} else if (lead.getExtraData() != null
					&& lead.getExtraData() instanceof SimpleOpportunity) {
				OpportunityLead associateLead = new OpportunityLead();
				associateLead.setOpportunityid(((SimpleOpportunity) lead
						.getExtraData()).getId());
				associateLead.setLeadid(lead.getId());
				associateLead.setCreatedtime(new GregorianCalendar().getTime());

				OpportunityService opportunityService = ApplicationContextUtil
						.getSpringBean(OpportunityService.class);
				opportunityService
						.saveOpportunityLeadRelationship(
								Arrays.asList(associateLead),
								AppContext.getAccountId());
			}
		} else {
			leadService.updateWithSession(lead, AppContext.getUsername());
		}

	}
}
