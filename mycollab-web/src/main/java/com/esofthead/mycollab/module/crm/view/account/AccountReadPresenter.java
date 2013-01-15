package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
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
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;
import java.util.Set;

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
                    public void onDelete(Account data) {
                        AccountService accountService = AppContext
                                .getSpringBean(AccountService.class);
                        accountService.removeWithSession(data.getId(),
                                AppContext.getUsername());
                        EventBus.getInstance().fireEvent(
                                new AccountEvent.GotoList(this, null));
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
                        criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
                        criteria.setId(new NumberSearchField(data.getId(), NumberSearchField.GREATHER));
                        Integer nextId = accountService.getNextItemKey(criteria);
                        if (nextId != null) {
                            EventBus.getInstance().fireEvent(
                                    new AccountEvent.GotoRead(this, nextId));
                        } else {
                            view.getWindow().showNotification("Information", "You are already in the last record", Window.Notification.TYPE_HUMANIZED_MESSAGE);
                        }

                    }

                    @Override
                    public void gotoPrevious(Account data) {
                        AccountService accountService = AppContext
                                .getSpringBean(AccountService.class);
                        AccountSearchCriteria criteria = new AccountSearchCriteria();
                        criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
                        criteria.setId(new NumberSearchField(data.getId(), NumberSearchField.LESSTHAN));
                        Integer nextId = accountService.getPreviousItemKey(criteria);
                        if (nextId != null) {
                            EventBus.getInstance().fireEvent(
                                    new AccountEvent.GotoRead(this, nextId));
                        } else {
                            view.getWindow().showNotification("Information", "You are already in the first record", Window.Notification.TYPE_HUMANIZED_MESSAGE);
                        }
                    }
                });

        view.getRelatedContactHandlers().addRelatedListHandler(
                new AbstractRelatedListHandler() {
                    @Override
                    public void createNewRelatedItem(String itemId) {
                        SimpleContact contact = new SimpleContact();
                        contact.setAccountId(view.getItem().getId());
                        EventBus.getInstance().fireEvent(
                                new ContactEvent.GotoEdit(this, contact));
                    }

                    @Override
                    public void selectAssociateItems(Set items) {
                        super.selectAssociateItems(items);
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
                new AbstractRelatedListHandler() {
                    @Override
                    public void createNewRelatedItem(String itemId) {
                        Lead lead = new Lead();
                        lead.setAccountname(view.getItem().getAccountname());
                        EventBus.getInstance().fireEvent(
                                new LeadEvent.GotoEdit(this, lead));
                    }
                });

        view.getRelatedCaseHandlers().addRelatedListHandler(
                new AbstractRelatedListHandler() {
                    @Override
                    public void createNewRelatedItem(String itemId) {
                        Case cases = new Case();
                        cases.setAccountid(view.getItem().getId());
                        EventBus.getInstance().fireEvent(
                                new CaseEvent.GotoEdit(this, cases));
                    }
                });

        view.getRelatedActivityHandlers().addRelatedListHandler(
                new AbstractRelatedListHandler() {
                    @Override
                    public void createNewRelatedItem(String itemId) {
                        if (itemId.equals("task")) {
                            Task task = new Task();
                            task.setType(CrmTypeConstants.ACCOUNT);
                            task.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(new ActivityEvent.TaskEdit(AccountReadPresenter.this, task));
                        } else if (itemId.equals("meeting")) {
                            Meeting meeting = new Meeting();
                            meeting.setType(CrmTypeConstants.ACCOUNT);
                            meeting.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(new ActivityEvent.MeetingEdit(AccountReadPresenter.this, meeting));
                        } else if (itemId.equals("call")) {
                            Call call = new Call();
                            call.setType(CrmTypeConstants.ACCOUNT);
                            call.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(new ActivityEvent.CallEdit(AccountReadPresenter.this, call));
                        }
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {

        if (data.getParams() instanceof Integer) {
            AccountService accountService = AppContext
                    .getSpringBean(AccountService.class);
            SimpleAccount account = accountService
                    .findAccountById((Integer) data.getParams());
            if (account != null) {
                super.onGo(container, data);
                view.previewItem((SimpleAccount) account);
            } else {
                AppContext.getApplication().getMainWindow().showNotification("Information", "The record is not existed", Window.Notification.TYPE_HUMANIZED_MESSAGE);
                return;
            }
        }
    }
}
