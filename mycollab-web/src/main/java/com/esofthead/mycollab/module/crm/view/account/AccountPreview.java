/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.contact.ContactListComp;
import com.esofthead.mycollab.module.crm.view.lead.LeadListComp;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityListComp;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author haiphucnguyen
 */
public class AccountPreview extends VerticalLayout {

    private SimpleAccount account;
    private PreviewForm previewForm;
    private ContactListComp associateContactList;
    private OpportunityListComp associateOpportunityList;
    private LeadListComp associateLeadList;
    private boolean isControlEnable = false;

    public AccountPreview(boolean enableButtonControls) {
        this.isControlEnable = enableButtonControls;
        constructUI();
    }

    private void constructUI() {
        previewForm = new PreviewForm();

        associateContactList = new ContactListComp();
        associateOpportunityList = new OpportunityListComp();
        associateLeadList = new LeadListComp();

        this.addComponent(previewForm);
    }

    public void previewItem(SimpleAccount item) {
        account = item;
        previewForm.setItemDataSource(new BeanItem<Account>(account));
        displayAssociateContactList();
        displayAssociateOpportunityList();
        displayAssociateLeadList();
    }

    public SimpleAccount getAccount() {
        return account;
    }

    public AdvancedPreviewBeanForm<Account> getPreviewForm() {
        return previewForm;
    }

    public ContactListComp getAssociateContactList() {
        return associateContactList;
    }

    public OpportunityListComp getAssociateOpportunityList() {
        return associateOpportunityList;
    }

    public LeadListComp getAssociateLeadList() {
        return associateLeadList;
    }

    private void displayAssociateContactList() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));
        criteria.setAccountId(new NumberSearchField(SearchField.AND, account
                .getId()));
        associateContactList.setSearchCriteria(criteria);
    }

    private void displayAssociateOpportunityList() {
        OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
        criteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));
        criteria.setAccountId(new NumberSearchField(SearchField.AND, account
                .getId()));
        associateOpportunityList.setSearchCriteria(criteria);
    }

    private void displayAssociateLeadList() {
        LeadSearchCriteria criteria = new LeadSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));
        criteria.setAccountName(new StringSearchField(SearchField.AND, account
                .getAccountname()));
        associateLeadList.setSearchCriteria(criteria);
    }

    private class PreviewForm extends AdvancedPreviewBeanForm<Account> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                private static final long serialVersionUID = 1L;

                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {
                    if (propertyId.equals("email")) {
                        return new DefaultFormViewFieldFactory.FormEmailLinkViewField(account.getEmail());
                    } else if (propertyId.equals("assignuser")) {
                        return new DefaultFormViewFieldFactory.FormLinkViewField(account
                                .getAssignUserFullName(),
                                new Button.ClickListener() {
                                    private static final long serialVersionUID = 1L;

                                    @Override
                                    public void buttonClick(Button.ClickEvent event) {
                                        // TODO Auto-generated method stub
                                    }
                                });
                    }

                    return null;
                }
            });
            super.setItemDataSource(newDataSource);
        }

        @Override
        protected void doPrint() {
            // Create a window that contains what you want to print
            Window window = new Window("Window to Print");

            AccountPreview printView = new AccountPreview(false);
            printView.previewItem(account);
            window.addComponent(printView);

            // Add the printing window as a new application-level window
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
        protected void showHistory() {
            HistoryLogWindow historyLog = new HistoryLogWindow(ModuleNameConstants.CRM, CrmTypeConstants.ACCOUNT, account.getId());

            getWindow().addWindow(historyLog);
        }

        class FormLayoutFactory extends AccountFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super(account.getAccountname());
            }

            @Override
            protected Layout createTopPanel() {
                if (isControlEnable) {
                    return (new PreviewFormControlsGenerator<Account>(
                            PreviewForm.this)).createButtonControls();
                } else {
                    return new HorizontalLayout();
                }

            }

            @Override
            protected Layout createBottomPanel() {
                VerticalLayout relatedItemsPanel = new VerticalLayout();
                relatedItemsPanel.setWidth("100%");

                relatedItemsPanel.addComponent(new NoteListItems(
                        "Notes", "Account", account.getId()));

                relatedItemsPanel.addComponent(associateContactList);
                relatedItemsPanel.addComponent(associateOpportunityList);
                relatedItemsPanel.addComponent(associateLeadList);

                return relatedItemsPanel;
            }
        }
    }
}
