package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class ContactReadViewImpl extends AbstractView implements
        ContactReadView {

    private static final long serialVersionUID = 1L;
    private PreviewForm previewForm;
    private SimpleContact contact;

    public ContactReadViewImpl() {
        super();
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }

    @Override
    public void previewItem(SimpleContact item) {
        this.contact = item;
        previewForm.setItemDataSource(new BeanItem<SimpleContact>(contact));
    }

    @Override
    public HasPreviewFormHandlers<Contact> getPreviewFormHandlers() {
        return previewForm;
    }

    private class PreviewForm extends AdvancedPreviewBeanForm<Contact> {

        private static final long serialVersionUID = 1L;

        @SuppressWarnings("serial")
        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {
                    if (propertyId.equals("accountid")) {
                        return new FormLinkViewField(contact.getAccountName(),
                                new Button.ClickListener() {
                                    @Override
                                    public void buttonClick(ClickEvent event) {
                                        EventBus.getInstance()
                                                .fireEvent(
                                                new AccountEvent.GotoRead(
                                                this,
                                                contact.getAccountid()));

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

        class FormLayoutFactory extends ContactFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super("Edit Contact");
            }

            @Override
            protected HorizontalLayout createTopPanel() {
                return (new PreviewFormControlsGenerator<Contact>(
                        PreviewForm.this)).createButtonControls();
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

    @Override
    public SimpleContact getItem() {
        return contact;
    }
}
