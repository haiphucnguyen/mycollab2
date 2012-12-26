package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Window;

@ViewComponent
public class AccountReadViewImpl extends AbstractView implements
        AccountReadView {

    private static final long serialVersionUID = 1L;
    private AccountPreview accountPreview;

    public AccountReadViewImpl() {
        super();
        accountPreview = new AccountPreview(true, this);
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
    public void doPrint() {
        // Create a window that contains what you want to print
        Window window = new Window("Window to Print");

        AccountPreview printView = new AccountPreview(false);
        printView.previewItem(getItem());
        // Have some content to print
        window.addComponent(printView);

        // Add the printing window as a new application-level
        // window
        getApplication().addWindow(window);

        // Open it as a popup window with no decorations
        getWindow().open(new ExternalResource(window.getURL()),
                "_blank", 1100, 200, // Width and height 
                Window.BORDER_NONE); // No decorations

        // Print automatically when the window opens.
        // This call will block until the print dialog exits!
        window.executeJavaScript("print();");

        // Close the window automatically after printing
        window.executeJavaScript("self.close();");
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
