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
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author haiphucnguyen
 */
public abstract class AccountPreviewBuilder extends VerticalLayout {

    protected SimpleAccount account;
    protected AdvancedPreviewBeanForm<Account> previewForm;
    protected AccountContactListComp associateContactList;
    protected AccountOpportunityListComp associateOpportunityList;
    protected AccountLeadListComp associateLeadList;
    protected AccountCaseListComp associateCaseList;
    protected EventRelatedItemListComp associateActivityList;
    protected NoteListItems noteListItems;

    protected void initRelatedComponent() {
        associateContactList = new AccountContactListComp();
        associateActivityList = new EventRelatedItemListComp(CrmTypeConstants.ACCOUNT, true);
        associateOpportunityList = new AccountOpportunityListComp();
        associateLeadList = new AccountLeadListComp();
        associateCaseList = new AccountCaseListComp();
        noteListItems = new NoteListItems("Notes");
    }

    public void previewItem(SimpleAccount item) {
        account = item;
        previewForm.setItemDataSource(new BeanItem<Account>(account));
        displayNotes();
        displayActivities();
        displayAssociateContactList();
        displayAssociateCaseList();
        displayAssociateOpportunityList();
        displayAssociateLeadList();
    }

    public SimpleAccount getAccount() {
        return account;
    }

    public AdvancedPreviewBeanForm<Account> getPreviewForm() {
        return previewForm;
    }

    public AccountContactListComp getAssociateContactList() {
        return associateContactList;
    }

    public AccountOpportunityListComp getAssociateOpportunityList() {
        return associateOpportunityList;
    }

    public AccountLeadListComp getAssociateLeadList() {
        return associateLeadList;
    }

    public AccountCaseListComp getAssociateCaseList() {
        return associateCaseList;
    }

    public EventRelatedItemListComp getAssociateActivityList() {
        return associateActivityList;
    }

    public void displayActivities() {
        EventSearchCriteria criteria = new EventSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        criteria.setType(new StringSearchField(SearchField.AND, CrmTypeConstants.ACCOUNT));
        criteria.setTypeid(new NumberSearchField(account.getId()));
        associateActivityList.setSearchCriteria(criteria);
    }

    private void displayNotes() {
        noteListItems.showNotes(CrmTypeConstants.ACCOUNT, account.getId());
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

    private void displayAssociateCaseList() {
        CaseSearchCriteria criteria = new CaseSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));
        criteria.setAccountId(new NumberSearchField(account.getId()));
        associateCaseList.setSearchCriteria(criteria);
    }

    protected class AccountFormFieldFactory extends DefaultFormViewFieldFactory {

        private static final long serialVersionUID = 1L;

        @Override
        protected Field onCreateField(Item item, Object propertyId,
                Component uiContext) {
            if (propertyId.equals("email")) {
                return new DefaultFormViewFieldFactory.FormEmailLinkViewField(
                        account.getEmail());
            } else if (propertyId.equals("assignuser")) {
                return new UserLinkViewField(account.getAssignuser(), account.getAssignUserFullName());
            }

            return null;
        }
    }

    /**
     *
     */
    public static class ReadView extends AccountPreviewBuilder {

        private final TabSheet tabContainer;
        private final VerticalLayout accountInformation;
        private final VerticalLayout relatedItemsContainer;
        private final AddViewLayout accountAddLayout;

        public ReadView() {
            accountAddLayout = new AddViewLayout("", new ThemeResource(
                    "icons/48/crm/account.png"));
            this.addComponent(accountAddLayout);

            tabContainer = new TabSheet();

            initRelatedComponent();

            previewForm = new AdvancedPreviewBeanForm<Account>() {
                @Override
                public void setItemDataSource(Item newDataSource) {
                    this.setFormLayoutFactory(new AccountFormLayoutFactory.AccountInformationLayout());
                    this.setFormFieldFactory(new AccountFormFieldFactory());
                    super.setItemDataSource(newDataSource);
                    accountAddLayout.setTitle(account.getAccountname());
                }

                @Override
                protected void doPrint() {
                    // Create a window that contains what you want to print
                    Window window = new Window("Window to Print");

                    AccountPreviewBuilder printView = new AccountPreviewBuilder.PrintView();
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
                    AccountHistoryLogWindow historyLog = new AccountHistoryLogWindow(
                            ModuleNameConstants.CRM, CrmTypeConstants.ACCOUNT,
                            account.getId());
                    getWindow().addWindow(historyLog);
                }
            };

            accountInformation = new VerticalLayout();
            accountInformation.setMargin(true);
            Layout actionControls = new PreviewFormControlsGenerator<Account>(
                    previewForm).createButtonControls();
            accountInformation.addComponent(actionControls);
            accountInformation.addComponent(previewForm);
            accountInformation.addComponent(noteListItems);

            tabContainer.addTab(accountInformation, "Account Information");

            relatedItemsContainer = new VerticalLayout();
            relatedItemsContainer.setMargin(true);
            relatedItemsContainer.addComponent(associateActivityList);
            relatedItemsContainer.addComponent(associateContactList);
            relatedItemsContainer.addComponent(associateOpportunityList);
            relatedItemsContainer.addComponent(associateCaseList);
            relatedItemsContainer.addComponent(associateLeadList);
            tabContainer.addTab(relatedItemsContainer, "More Information");

            accountAddLayout.addBody(tabContainer);
        }
    }

    /**
     *
     */
    public static class PrintView extends AccountPreviewBuilder {

        public PrintView() {
            previewForm = new AdvancedPreviewBeanForm<Account>() {
                @Override
                public void setItemDataSource(Item newDataSource) {
                    this.setFormLayoutFactory(new FormLayoutFactory());
                    this.setFormFieldFactory(new AccountFormFieldFactory());
                    super.setItemDataSource(newDataSource);
                }
            };
            initRelatedComponent();

            this.addComponent(previewForm);
        }

        class FormLayoutFactory extends AccountFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super(account.getAccountname());
            }

            @Override
            protected Layout createTopPanel() {
                return null;
            }

            @Override
            protected Layout createBottomPanel() {
                VerticalLayout relatedItemsPanel = new VerticalLayout();
                relatedItemsPanel.setWidth("100%");

                relatedItemsPanel.addComponent(noteListItems);
                relatedItemsPanel.addComponent(associateActivityList);
                relatedItemsPanel.addComponent(associateContactList);
                relatedItemsPanel.addComponent(associateOpportunityList);
                relatedItemsPanel.addComponent(associateCaseList);
                relatedItemsPanel.addComponent(associateLeadList);

                return relatedItemsPanel;
            }
        }
    }
}
