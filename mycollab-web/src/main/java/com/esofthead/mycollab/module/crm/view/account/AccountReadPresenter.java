package com.esofthead.mycollab.module.crm.view.account;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.AccountContact;
import com.esofthead.mycollab.module.crm.domain.AccountLead;
import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.CaseWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.view.AbstractRelatedListHandler;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class AccountReadPresenter extends CrmGenericPresenter<AccountReadView> {

	private static final long serialVersionUID = 1L;

	public AccountReadPresenter() {
		super(AccountReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<Account>() {
					@Override
					public void onEdit(Account data) {
						EventBus.getInstance().fireEvent(
								new AccountEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(final Account data) {
						ConfirmDialog.show(
								view.getWindow(),
								"Please Confirm:",
								"Are you sure to delete account '"
										+ data.getAccountname() + "' ?", "Yes",
								"No", new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											AccountService accountService = AppContext
													.getSpringBean(AccountService.class);
											accountService.removeWithSession(
													data.getId(),
													AppContext.getUsername());
											EventBus.getInstance().fireEvent(
													new AccountEvent.GotoList(
															this, null));
										}
									}
								});

					}

					@Override
					public void onClone(Account data) {
						Account cloneData = (Account) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new AccountEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new AccountEvent.GotoList(this, null));
					}

					@Override
					public void gotoNext(Account data) {
						AccountService accountService = AppContext
								.getSpringBean(AccountService.class);
						AccountSearchCriteria criteria = new AccountSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = accountService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoRead(this, nextId));
						} else {
							view.getWindow().showNotification(AppContext.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
									AppContext.getMessage(GenericI18Enum.INFORMATION_GOTO_LAST_RECORD),
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}

					}

					@Override
					public void gotoPrevious(Account data) {
						AccountService accountService = AppContext
								.getSpringBean(AccountService.class);
						AccountSearchCriteria criteria = new AccountSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = accountService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoRead(this, nextId));
						} else {
							view.getWindow().showNotification(AppContext.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
									AppContext.getMessage(GenericI18Enum.INFORMATION_GOTO_FIRST_RECORD),
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}
					}
				});

		view.getRelatedContactHandlers().addRelatedListHandler(
				new AbstractRelatedListHandler<SimpleContact>() {
					@Override
					public void createNewRelatedItem(String itemId) {
						SimpleContact contact = new SimpleContact();
						contact.setAccountId(view.getItem().getId());
						EventBus.getInstance().fireEvent(
								new ContactEvent.GotoEdit(this, contact));
					}

					@Override
					public void selectAssociateItems(Set<SimpleContact> items) {
						if (items.size() > 0) {
							SimpleAccount account = view.getItem();
							List<AccountContact> associateContacts = new ArrayList<AccountContact>();
							for (SimpleContact contact : items) {
								AccountContact assoContact = new AccountContact();
								assoContact.setAccountid(account.getId());
								assoContact.setContactid(contact.getId());
								assoContact
										.setCreatedtime(new GregorianCalendar()
												.getTime());
								associateContacts.add(assoContact);
							}

							AccountService accountService = AppContext
									.getSpringBean(AccountService.class);
							accountService
									.saveAccountContactRelationship(associateContacts);

							view.getRelatedContactHandlers().refresh();
						}
					}
				});

		view.getRelatedOpportunityHandlers().addRelatedListHandler(
				new AbstractRelatedListHandler() {
					@Override
					public void createNewRelatedItem(String itemId) {
						Opportunity opportunity = new Opportunity();
						opportunity.setAccountid(view.getItem().getId());
						EventBus.getInstance()
								.fireEvent(
										new OpportunityEvent.GotoEdit(this,
												opportunity));
					}
				});

		view.getRelatedLeadHandlers().addRelatedListHandler(
				new AbstractRelatedListHandler<SimpleLead>() {
					@Override
					public void createNewRelatedItem(String itemId) {
						Lead lead = new Lead();
						lead.setAccountname(view.getItem().getAccountname());
						EventBus.getInstance().fireEvent(
								new LeadEvent.GotoEdit(this, lead));
					}

					@Override
					public void selectAssociateItems(Set<SimpleLead> items) {
						if (items.size() > 0) {
							SimpleAccount account = view.getItem();
							List<AccountLead> associateLeads = new ArrayList<AccountLead>();
							for (SimpleLead contact : items) {
								AccountLead assoLead = new AccountLead();
								assoLead.setAccountid(account.getId());
								assoLead.setLeadid(contact.getId());
								assoLead.setCreatetime(new GregorianCalendar()
										.getTime());
								associateLeads.add(assoLead);
							}

							AccountService accountService = AppContext
									.getSpringBean(AccountService.class);
							accountService
									.saveAccountLeadRelationship(associateLeads);

							view.getRelatedContactHandlers().refresh();
						}
					}
				});

		view.getRelatedCaseHandlers().addRelatedListHandler(
				new AbstractRelatedListHandler() {
					@Override
					public void createNewRelatedItem(String itemId) {
						CaseWithBLOBs cases = new CaseWithBLOBs();
						cases.setAccountid(view.getItem().getId());
						EventBus.getInstance().fireEvent(
								new CaseEvent.GotoEdit(this, cases));
					}
				});

		view.getRelatedActivityHandlers().addRelatedListHandler(
				new AbstractRelatedListHandler() {
					@Override
					public void createNewRelatedItem(final String itemId) {
						if (itemId.equals("task")) {
							final Task task = new Task();
							task.setType(CrmTypeConstants.ACCOUNT);
							task.setTypeid(view.getItem().getId());
							EventBus.getInstance().fireEvent(
									new ActivityEvent.TaskEdit(
											AccountReadPresenter.this, task));
						} else if (itemId.equals("meeting")) {
							final Meeting meeting = new Meeting();
							meeting.setType(CrmTypeConstants.ACCOUNT);
							meeting.setTypeid(view.getItem().getId());
							EventBus.getInstance()
									.fireEvent(
											new ActivityEvent.MeetingEdit(
													AccountReadPresenter.this,
													meeting));
						} else if (itemId.equals("call")) {
							final CallWithBLOBs call = new CallWithBLOBs();
							call.setType(CrmTypeConstants.ACCOUNT);
							call.setTypeid(view.getItem().getId());
							EventBus.getInstance().fireEvent(
									new ActivityEvent.CallEdit(
											AccountReadPresenter.this, call));
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {

		if (AppContext.canRead(RolePermissionCollections.CRM_ACCOUNT)) {
			if (data.getParams() instanceof Integer) {
				AccountService accountService = AppContext
						.getSpringBean(AccountService.class);
				SimpleAccount account = accountService
						.findAccountById((Integer) data.getParams());
				if (account != null) {
					super.onGo(container, data);
					view.previewItem((SimpleAccount) account);
					AppContext.addFragment("crm/account/preview/"
							+ UrlEncodeDecoder.encode(account.getId()),
							"Preview account: " + account.getAccountname());
				} else {
					AppContext
							.getApplication()
							.getMainWindow()
							.showNotification(
									AppContext
											.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
									AppContext
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
