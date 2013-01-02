package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class AccountReadViewImpl extends AbstractView implements
        AccountReadView {

    private static final long serialVersionUID = 1L;
    private AccountPreview accountPreview;

    public AccountReadViewImpl() {
        super();
        accountPreview = new AccountPreview(true);
        this.addComponent(accountPreview);
    }

    @Override
    public void previewItem(SimpleAccount item) {
        accountPreview.previewItem(item);
    }

    @Override
    public HasPreviewFormHandlers<Account> getPreviewFormHandlers() {
        return accountPreview.getPreviewForm();
    }

    @Override
    public SimpleAccount getItem() {
        return accountPreview.getAccount();
    }

    @Override
    public IRelatedListHandlers getRelatedContactHandlers() {
        return accountPreview.getAssociateContactList();
    }

    @Override
    public IRelatedListHandlers getRelatedOpportunityHandlers() {
        return accountPreview.getAssociateOpportunityList();
    }

    @Override
    public IRelatedListHandlers getRelatedLeadHandlers() {
        return accountPreview.getAssociateLeadList();
    }
}
