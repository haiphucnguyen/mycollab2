package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

@ViewComponent
public class ContactAddViewImpl extends AbstractView implements
        ContactAddView {

    private static final long serialVersionUID = 1L;
    private EditForm editForm;
    private Contact contact;

    public ContactAddViewImpl() {
        super();
        editForm = new EditForm();
        this.addComponent(editForm);
    }

    @Override
    public void editItem(Contact item) {
        this.contact = item;
        if (editForm.isCheckToCopy) editForm.isCheckToCopy = false;
        editForm.setItemDataSource(new BeanItem<Contact>(contact));

    }

    @Override
    public HasEditFormHandlers<Contact> getEditFormHandlers() {
        return editForm;
    }

    private class EditForm extends AdvancedEditBeanForm<Contact> {

        private static final long serialVersionUID = 1L;
        private boolean isCheckToCopy;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new ContactEditFormFieldFactory(contact));
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory extends ContactFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super((contact.getId() == null) ? "Create Contact" : (contact.getFirstname() + " " + contact.getLastname()));
            }

            private Layout createButtonControls() {
            	final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<Contact>(
						EditForm.this)).createButtonControls();
				controlButtons.setSizeUndefined();
				controlPanel.addComponent(controlButtons);
				controlPanel.setWidth("100%");
				controlPanel.setComponentAlignment(controlButtons,
						Alignment.MIDDLE_CENTER);
				return controlPanel;
            }

            @Override
            protected Layout createTopPanel() {
                return createButtonControls();
            }

            @Override
            protected Layout createBottomPanel() {
                return createButtonControls();
            }
        }
    }
}
