package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.RelatedListHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class AccountReadPresenter extends CrmGenericPresenter<AccountReadView> {
    
    private static final long serialVersionUID = 1L;
    
    public AccountReadPresenter() {
        super(AccountReadView.class);
        bind();
    }
    
    private void bind() {
        view.getPreviewFormHandlers().addFormHandler(
                new PreviewFormHandlers<Account>() {
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
                });
        
        view.getRelatedContactHandlers().addRelatedListHandler(
                new RelatedListHandler() {
                    @Override
                    public void createNewRelatedItem(String itemId) {
                        SimpleContact contact = new SimpleContact();
                        contact.setAccountId(view.getItem().getId());
                        EventBus.getInstance().fireEvent(
                                new ContactEvent.GotoEdit(this, contact));
                    }
                });
        
        view.getRelatedOpportunityHandlers().addRelatedListHandler(
                new RelatedListHandler() {
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
                new RelatedListHandler() {
                    @Override
                    public void createNewRelatedItem(String itemId) {
                        Lead lead = new Lead();
                        lead.setAccountname(view.getItem().getAccountname());
                        EventBus.getInstance().fireEvent(
                                new LeadEvent.GotoEdit(this, lead));
                    }
                });
        
        view.getRelatedCaseHandlers().addRelatedListHandler(
                new RelatedListHandler() {
                    @Override
                    public void createNewRelatedItem(String itemId) {
                        Case cases = new Case();
                        cases.setAccountid(view.getItem().getId());
                        EventBus.getInstance().fireEvent(
                                new CaseEvent.GotoEdit(this, cases));
                    }
                });
        
        view.getRelatedActivityHandlers().addRelatedListHandler(
                new RelatedListHandler() {
                    @Override
                    public void createNewRelatedItem(String itemId) {
                        if (itemId.equals("task")) {
                            Task task = new Task();
                            task.setType(CrmTypeConstants.ACCOUNT);
                            task.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(new ActivityEvent.TaskEdit(AccountReadPresenter.this, task));
                        } else if (itemId.equals("meeting")) {
                            
                        }
                    }
                });
    }
    
    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        super.onGo(container, data);
        
        if (data.getParams() instanceof Integer) {
            AccountService accountService = AppContext
                    .getSpringBean(AccountService.class);
            SimpleAccount account = accountService
                    .findAccountById((Integer) data.getParams());
            view.previewItem((SimpleAccount) account);
        }
    }
}
