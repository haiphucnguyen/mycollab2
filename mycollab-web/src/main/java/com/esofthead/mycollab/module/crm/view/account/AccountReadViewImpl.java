package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class AccountReadViewImpl extends AbstractView implements
        AccountReadView {

    private static final long serialVersionUID = 1L;
    private AccountPreviewBuilder.ReadView accountPreview;

    public AccountReadViewImpl() {
        super();
        accountPreview = new AccountPreviewBuilder.ReadView();
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
    public IRelatedListHandlers<SimpleContact> getRelatedContactHandlers() {
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

    @Override
    public IRelatedListHandlers getRelatedCaseHandlers() {
        return accountPreview.getAssociateCaseList();
    }

    @Override
    public IRelatedListHandlers getRelatedActivityHandlers() {
        return accountPreview.getAssociateActivityList();
    }
}
