package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public abstract class ContactPreviewBuilder extends VerticalLayout {

    protected AdvancedPreviewBeanForm<Contact> previewForm;
    protected SimpleContact contact;
    protected ContactOpportunityListComp associateOpportunityList;
    protected NoteListItems noteListItems;

    protected void initRelatedComponent() {
        associateOpportunityList = new ContactOpportunityListComp();
        noteListItems = new NoteListItems("Notes");
    }

    public void previewItem(SimpleContact item) {
        contact = item;
        previewForm.setItemDataSource(new BeanItem<SimpleContact>(contact));
        displayNotes();
        displayAssociateOpportunityList();
    }

    public SimpleContact getContact() {
        return contact;
    }

    public AdvancedPreviewBeanForm<Contact> getPreviewForm() {
        return previewForm;
    }

    private void displayNotes() {
        noteListItems.showNotes(CrmTypeConstants.ACCOUNT, contact.getId());
    }

    private void displayAssociateOpportunityList() {
        OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
        criteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));
        criteria.setContactId(new NumberSearchField(SearchField.AND, contact
                .getId()));
        associateOpportunityList.setSearchCriteria(criteria);
    }

    protected class ContactFormFieldFactory extends DefaultFormViewFieldFactory {

        @Override
        protected Field onCreateField(Item item, Object propertyId,
                Component uiContext) {
            if (propertyId.equals("accountId")) {
                return new FormLinkViewField(contact.getAccountName(),
                        new Button.ClickListener() {
                            @Override
                            public void buttonClick(ClickEvent event) {
                                EventBus.getInstance()
                                        .fireEvent(
                                        new AccountEvent.GotoRead(
                                        this,
                                        contact.getAccountId()));

                            }
                        });
            } else if (propertyId.equals("email")) {
                return new FormEmailLinkViewField(contact.getEmail());
            } else if (propertyId.equals("assignuser")) {
                return new FormLinkViewField(contact
                        .getAssignUserFullName(),
                        new Button.ClickListener() {
                            @Override
                            public void buttonClick(ClickEvent event) {
                                // TODO Auto-generated method stub
                            }
                        });
            }

            return null;
        }
    }

    public static class ReadView extends ContactPreviewBuilder {
        private TabSheet tabContainer;
        private VerticalLayout accountInformation;
        private VerticalLayout relatedItemsContainer;
        private AddViewLayout accountAddLayout;
        
        public ReadView() {
            accountAddLayout = new AddViewLayout("", new ThemeResource("icons/48/crm/account.png"));
            this.addComponent(accountAddLayout);

            tabContainer = new TabSheet();
            initRelatedComponent();

            previewForm = new AdvancedPreviewBeanForm<Contact>() {
                @Override
                public void setItemDataSource(Item newDataSource) {
                    this.setFormLayoutFactory(new ContactFormLayoutFactory.ContactInformationLayout());
                    this.setFormFieldFactory(new ContactFormFieldFactory());
                    super.setItemDataSource(newDataSource);
                    accountAddLayout.setTitle(contact.getContactName());
                }

                @Override
                protected void doPrint() {
                    // Create a window that contains what you want to print
                    Window window = new Window("Window to Print");

                    ContactPreviewBuilder printView = new ContactPreviewBuilder.PrintView();
                    printView.previewItem(contact);
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
                    ContactHistoryLogWindow historyLog = new ContactHistoryLogWindow(ModuleNameConstants.CRM, CrmTypeConstants.CONTACT, contact.getId());
                    getWindow().addWindow(historyLog);
                }
            };

            accountInformation = new VerticalLayout();
            Layout actionControls = new PreviewFormControlsGenerator<Contact>(
                    previewForm).createButtonControls();
            accountInformation.addComponent(actionControls);
            accountInformation.addComponent(previewForm);
            accountInformation.addComponent(noteListItems);

            tabContainer.addTab(accountInformation, "Contact Information");



            relatedItemsContainer = new VerticalLayout();
            relatedItemsContainer.addComponent(associateOpportunityList);
            tabContainer.addTab(relatedItemsContainer, "More Information");

            accountAddLayout.addBody(tabContainer);
        }
    }
    
    public static class PrintView extends ContactPreviewBuilder {

        public PrintView() {
            previewForm = new AdvancedPreviewBeanForm<Contact>() {
                @Override
                public void setItemDataSource(Item newDataSource) {
                    this.setFormLayoutFactory(new FormLayoutFactory());
                    this.setFormFieldFactory(new ContactFormFieldFactory());
                    super.setItemDataSource(newDataSource);
                }
            };
            initRelatedComponent();

            this.addComponent(previewForm);
        }

        class FormLayoutFactory extends ContactFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super(contact.getContactName());
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
                relatedItemsPanel.addComponent(associateOpportunityList);

                return relatedItemsPanel;
            }
        }
    }
}
