package com.esofthead.mycollab.module.crm.view.lead;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.CrmLinkGenerator;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.CampaignLead;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.view.AbstractRelatedListHandler;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class LeadReadPresenter extends CrmGenericPresenter<LeadReadView> {

	private static final long serialVersionUID = 1L;

	public LeadReadPresenter() {
		super(LeadReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<Lead>() {
					@Override
					public void onEdit(Lead data) {
						EventBus.getInstance().fireEvent(
								new LeadEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(final Lead data) {
						ConfirmDialogExt.show(
								view.getWindow(),
								LocalizationHelper.getMessage(
										GenericI18Enum.DELETE_DIALOG_TITLE,
										SiteConfiguration.getSiteName()),
								LocalizationHelper
										.getMessage(GenericI18Enum.CONFIRM_DELETE_RECORD_DIALOG_MESSAGE),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
								new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											LeadService LeadService = ApplicationContextUtil
													.getSpringBean(LeadService.class);
											LeadService.removeWithSession(
													data.getId(),
													AppContext.getUsername(),
													AppContext.getAccountId());
											EventBus.getInstance().fireEvent(
													new LeadEvent.GotoList(
															this, null));
										}
									}
								});
					}

					@Override
					public void onClone(Lead data) {
						Lead cloneData = (Lead) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new LeadEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new LeadEvent.GotoList(this, null));
					}

					@Override
					public void gotoNext(Lead data) {
						LeadService contactService = ApplicationContextUtil
								.getSpringBean(LeadService.class);
						LeadSearchCriteria criteria = new LeadSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = contactService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new LeadEvent.GotoRead(this, nextId));
						} else {
							NotificationUtil.showGotoLastRecordNotification();
						}

					}

					@Override
					public void gotoPrevious(Lead data) {
						LeadService contactService = ApplicationContextUtil
								.getSpringBean(LeadService.class);
						LeadSearchCriteria criteria = new LeadSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = contactService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new LeadEvent.GotoRead(this, nextId));
						} else {
							NotificationUtil.showGotoFirstRecordNotification();
						}
					}
				});

		view.getRelatedActivityHandlers().addRelatedListHandler(
				new AbstractRelatedListHandler() {
					@Override
					public void createNewRelatedItem(String itemId) {
						if (itemId.equals("task")) {
							Task task = new Task();
							task.setType(CrmTypeConstants.LEAD);
							task.setTypeid(view.getItem().getId());
							EventBus.getInstance().fireEvent(
									new ActivityEvent.TaskEdit(
											LeadReadPresenter.this, task));
						} else if (itemId.equals("meeting")) {
							Meeting meeting = new Meeting();
							meeting.setType(CrmTypeConstants.LEAD);
							meeting.setTypeid(view.getItem().getId());
							EventBus.getInstance().fireEvent(
									new ActivityEvent.MeetingEdit(
											LeadReadPresenter.this, meeting));
						} else if (itemId.equals("call")) {
							CallWithBLOBs call = new CallWithBLOBs();
							call.setType(CrmTypeConstants.LEAD);
							call.setTypeid(view.getItem().getId());
							EventBus.getInstance().fireEvent(
									new ActivityEvent.CallEdit(
											LeadReadPresenter.this, call));
						}
					}
				});

		view.getRelatedCampaignHandlers().addRelatedListHandler(
				new AbstractRelatedListHandler<SimpleCampaign>() {
					@Override
					public void createNewRelatedItem(String itemId) {
						CampaignWithBLOBs campaign = new CampaignWithBLOBs();
						campaign.setExtraData(view.getItem());
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoEdit(
										LeadReadPresenter.this, campaign));
					}

					@Override
					public void selectAssociateItems(Set<SimpleCampaign> items) {
						if (!items.isEmpty()) {
							SimpleLead lead = view.getItem();
							List<CampaignLead> associateCampaigns = new ArrayList<CampaignLead>();
							for (SimpleCampaign campaign : items) {
								CampaignLead associateCampaign = new CampaignLead();
								associateCampaign.setCampaignid(campaign
										.getId());
								associateCampaign.setLeadid(lead.getId());
								associateCampaign
										.setCreatedtime(new GregorianCalendar()
												.getTime());
								associateCampaigns.add(associateCampaign);
							}

							CampaignService campaignService = ApplicationContextUtil
									.getSpringBean(CampaignService.class);
							campaignService.saveCampaignLeadRelationship(
									associateCampaigns,
									AppContext.getAccountId());
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_LEAD)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_LEADS_HEADER));

			if (data.getParams() instanceof Integer) {
				LeadService leadService = ApplicationContextUtil
						.getSpringBean(LeadService.class);
				SimpleLead lead = leadService.findById(
						(Integer) data.getParams(), AppContext.getAccountId());
				if (lead != null) {
					super.onGo(container, data);
					view.previewItem(lead);

					AppContext.addFragment(CrmLinkGenerator
							.generateLeadPreviewLink(lead.getId()),
							LocalizationHelper.getMessage(
									GenericI18Enum.BROWSER_PREVIEW_ITEM_TITLE,
									"Lead", lead.getLeadName()));
				} else {
					NotificationUtil.showRecordNotExistNotification();
					return;
				}
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}
