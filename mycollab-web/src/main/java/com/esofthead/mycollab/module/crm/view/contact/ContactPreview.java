package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ContactPreview extends VerticalLayout {

    private PreviewForm previewForm;
    private SimpleContact contact;
    private boolean isControlEnable = false;

    public ContactPreview(Boolean enableButtonControl) {
        this.isControlEnable = enableButtonControl;
        constructUI();
    }

    private void constructUI() {
        previewForm = new PreviewForm();

        this.addComponent(previewForm);
    }

    public void previewItem(SimpleContact item) {
        contact = item;
        previewForm.setItemDataSource(new BeanItem<SimpleContact>(contact));
    }

    public SimpleContact getContact() {
        return contact;
    }

    public AdvancedPreviewBeanForm<Contact> getPreviewForm() {
        return previewForm;
    }

    public class PreviewForm extends AdvancedPreviewBeanForm<Contact> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
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
            });
            super.setItemDataSource(newDataSource);
        }

        @Override
        protected void doPrint() {
            // Create a window that contains what you want to print
            Window window = new Window("Window to Print");

            ContactPreview printView = new ContactPreview(false);
            printView.previewItem(contact);
            window.addComponent(printView);

            // Add the printing window as a new application-level window
            getApplication().addWindow(window);

            // Open it as a popup window with no decorations
            getWindow().open(new ExternalResource(window.getURL()), "_blank",
                    1100, 200, // Width and height
                    Window.BORDER_NONE); // No decorations

            // Print automatically when the window opens.
            // This call will block until the print dialog exits!
            window.executeJavaScript("print();");

            // Close the window automatically after printing
            window.executeJavaScript("self.close();");
        }

        @Override
        protected void showHistory() {
            HistoryLogWindow historyLog = new HistoryLogWindow(
                    ModuleNameConstants.CRM, CrmTypeConstants.CONTACT,
                    contact.getId());

            getWindow().addWindow(historyLog);
        }

        class FormLayoutFactory extends ContactFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super("Edit Contact");
            }

            @Override
            protected HorizontalLayout createTopPanel() {
                if (isControlEnable) {
                    return (new PreviewFormControlsGenerator<Contact>(
                            PreviewForm.this)).createButtonControls();
                } else {
                    return new HorizontalLayout();
                }

            }

            @Override
            protected Layout createBottomPanel() {
                VerticalLayout relatedItemsPanel = new VerticalLayout();

                relatedItemsPanel.addComponent(new NoteListItems("Notes",
                        "Contact", contact.getId()));
                return relatedItemsPanel;
            }
        }
    }
}
