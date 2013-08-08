package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.CampaignAccount;
import com.esofthead.mycollab.module.crm.domain.CampaignContact;
import com.esofthead.mycollab.module.crm.domain.CampaignLead;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.view.AbstractRelatedListHandler;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmLinkGenerator;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class CampaignReadPresenter extends
		CrmGenericPresenter<CampaignReadView> {

	private static final long serialVersionUID = 1L;

	public CampaignReadPresenter() {
		super(CampaignReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<CampaignWithBLOBs>() {
					@Override
					public void onEdit(CampaignWithBLOBs data) {
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(final CampaignWithBLOBs data) {
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
											CampaignService campaignService = AppContext
													.getSpringBean(CampaignService.class);
											campaignService.removeWithSession(
													data.getId(),
													AppContext.getUsername());
											EventBus.getInstance().fireEvent(
													new CampaignEvent.GotoList(
															this, null));
										}
									}
								});
					}

					@Override
					public void onClone(CampaignWithBLOBs data) {
						CampaignWithBLOBs cloneData = (CampaignWithBLOBs) data
								.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoList(this, null));
					}

					@Override
					public void gotoNext(CampaignWithBLOBs data) {
						CampaignService contactService = AppContext
								.getSpringBean(CampaignService.class);
						CampaignSearchCriteria criteria = new CampaignSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = contactService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new CampaignEvent.GotoRead(this, nextId));
						} else {
							view.getWindow()
									.showNotification(
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_GOTO_LAST_RECORD),
											Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}

					}

					@Override
					public void gotoPrevious(CampaignWithBLOBs data) {
						CampaignService contactService = AppContext
								.getSpringBean(CampaignService.class);
						CampaignSearchCriteria criteria = new CampaignSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = contactService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new CampaignEvent.GotoRead(this, nextId));
						} else {
							view.getWindow()
									.showNotification(
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_GOTO_FIRST_RECORD),
											Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}
					}
				});

		view.getRelatedActivityHandlers().addRelatedListHandler(
				new AbstractRelatedListHandler() {
					@Override
					public void createNewRelatedItem(String itemId) {
						if (itemId.equals("task")) {
							Task task = new Task();
							task.setType(CrmTypeConstants.CAMPAIGN);
							task.setTypeid(view.getItem().getId());
							EventBus.getInstance().fireEvent(
									new ActivityEvent.TaskEdit(
											CampaignReadPresenter.this, task));
						} else if (itemId.equals("meeting")) {
							Meeting meeting = new Meeting();
							meeting.setType(CrmTypeConstants.CAMPAIGN);
							meeting.setTypeid(view.getItem().getId());
							EventBus.getInstance()
									.fireEvent(
											new ActivityEvent.MeetingEdit(
													CampaignReadPresenter.this,
													meeting));
						} else if (itemId.equals("call")) {
							CallWithBLOBs call = new CallWithBLOBs();
							call.setType(CrmTypeConstants.CAMPAIGN);
							call.setTypeid(view.getItem().getId());
							EventBus.getInstance().fireEvent(
									new ActivityEvent.CallEdit(
											CampaignReadPresenter.this, call));
						}
					}
				});

		view.getRelatedAccountHandlers().addRelatedListHandler(
				new AbstractRelatedListHandler<SimpleAccount>() {
					@Override
					public void createNewRelatedItem(String itemId) {
						Account account = new Account();
						account.setExtraData(view.getItem());
						EventBus.getInstance().fireEvent(
								new AccountEvent.GotoEdit(
										CampaignReadPresenter.this, account));

					}

					@Override
					public void selectAssociateItems(Set<SimpleAccount> items) {
						if (items.size() > 0) {
							SimpleCampaign campaign = view.getItem();
							List<CampaignAccount> associateAccounts = new ArrayList<CampaignAccount>();
							for (SimpleAccount account : items) {
								CampaignAccount assoAccount = new CampaignAccount();
								assoAccount.setAccountid(account.getId());
								assoAccount.setCampaignid(campaign.getId());
								assoAccount
										.setCreatedtime(new GregorianCalendar()
												.getTime());
								associateAccounts.add(assoAccount);
							}

							CampaignService accountService = AppContext
									.getSpringBean(CampaignService.class);
							accountService
									.saveCampaignAccountRelationship(associateAccounts);

							view.getRelatedAccountHandlers().refresh();
						}
					}
				});

		view.getRelatedContactHandlers().addRelatedListHandler(
				new AbstractRelatedListHandler<SimpleContact>() {

					@Override
					public void createNewRelatedItem(String itemId) {
						Contact contact = new Contact();
						contact.setExtraData(view.getItem());
						EventBus.getInstance().fireEvent(
								new ContactEvent.GotoEdit(
										CampaignReadPresenter.this, contact));
					}

					@Override
					public void selectAssociateItems(Set<SimpleContact> items) {
						if (items.size() > 0) {
							SimpleCampaign campaign = view.getItem();
							List<CampaignContact> associateContacts = new ArrayList<CampaignContact>();
							for (SimpleContact contact : items) {
								CampaignContact associateContact = new CampaignContact();
								associateContact.setCampaignid(campaign.getId());
								associateContact.setContactid(contact.getId());
								associateContact
										.setCreatedtime(new GregorianCalendar()
												.getTime());
								associateContacts.add(associateContact);
							}

							CampaignService campaignService = AppContext
									.getSpringBean(CampaignService.class);
							campaignService
									.saveCampaignContactRelationship(associateContacts);

							view.getRelatedContactHandlers().refresh();
						}
					}
				});

		view.getRelatedLeadHandlers().addRelatedListHandler(
				new AbstractRelatedListHandler<SimpleLead>() {
					@Override
					public void createNewRelatedItem(String itemId) {
						Lead lead = new Lead();
						lead.setExtraData(view.getItem());
						EventBus.getInstance().fireEvent(
								new LeadEvent.GotoEdit(
										CampaignReadPresenter.this, lead));
					}

					@Override
					public void selectAssociateItems(Set<SimpleLead> items) {
						if (items.size() > 0) {
							SimpleCampaign campaign = view.getItem();
							List<CampaignLead> associateLeads = new ArrayList<CampaignLead>();
							for (SimpleLead lead : items) {
								CampaignLead associateLead = new CampaignLead();
								associateLead.setCampaignid(campaign.getId());
								associateLead.setLeadid(lead.getId());
								associateLead
										.setCreatedtime(new GregorianCalendar()
												.getTime());
								associateLeads.add(associateLead);
							}

							CampaignService campaignService = AppContext
									.getSpringBean(CampaignService.class);
							campaignService
									.saveCampaignLeadRelationship(associateLeads);

							view.getRelatedLeadHandlers().refresh();
						}
					}
				});

	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_CAMPAIGN)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_CAMPAIGNS_HEADER));

			if (data.getParams() instanceof Integer) {
				CampaignService campaignService = AppContext
						.getSpringBean(CampaignService.class);
				SimpleCampaign campaign = campaignService
						.findById((Integer) data.getParams());
				if (campaign != null) {
					super.onGo(container, data);
					view.previewItem(campaign);
					AppContext.addFragment(CrmLinkGenerator
							.generateCampaignPreviewLink(campaign.getId()),
							LocalizationHelper.getMessage(
									GenericI18Enum.BROWSER_PREVIEW_ITEM_TITLE,
									"Campaign", campaign.getCampaignname()));
				} else {
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
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}
