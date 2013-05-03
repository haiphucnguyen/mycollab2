package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
import com.esofthead.mycollab.module.crm.view.lead.LeadSourceComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.CheckboxField;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

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
            this.setFormFieldFactory(new EditFormFieldFactory());
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory extends ContactFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super((contact.getId() == null) ? "Create Contact" : (contact.getFirstname() + " " + contact.getLastname()));
            }

            private Layout createButtonControls() {
                return (new EditFormControlsGenerator<Contact>(EditForm.this))
                        .createButtonControls();
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

        private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

            private static final long serialVersionUID = 1L;

            @Override
            protected Field onCreateField(Item item, Object propertyId,
                    com.vaadin.ui.Component uiContext) {

                if (propertyId.equals("leadsource")) {
                    LeadSourceComboBox leadSource = new LeadSourceComboBox();
                    return leadSource;
                } else if (propertyId.equals("contactId")) {
                    AccountSelectionField contactField = new AccountSelectionField();
//                    if (contact.getAccountId() != null) {
//                        AccountService contactService = AppContext
//                                .getSpringBean(AccountService.class);
//                        SimpleAccount contact = contactService
//                                .findAccountById(contact.getAccountId());
//                        if (contact != null) {
//                            contactField.setAccount(contact);
//                        }
//                    }
                    return contactField;
                } else if (propertyId.equals("lastname")) {
                    TextField tf = new TextField();
                    tf.setNullRepresentation("");
                    tf.setRequired(true);
                    tf.setRequiredError("Last name must not be null");
                    return tf;
                } else if (propertyId.equals("description")) {
                    TextArea descArea = new TextArea();
                    descArea.setNullRepresentation("");
                    return descArea;
                } else if (propertyId.equals("assignuser")) {
                    UserComboBox userBox = new UserComboBox();
                    userBox.select(contact.getAssignuser());
                    return userBox;
                } else if (propertyId.equals("primcountry")
                        || propertyId.equals("othercountry")) {
                	CountryComboBox otherCountryComboBox = new CountryComboBox();
                    return otherCountryComboBox;
                } else if ("id".equals(propertyId)) {
                	final CheckboxField chkField = new CheckboxField();
                	chkField.getCheckBox().addListener(new Button.ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {
							if ((Boolean) chkField.getCheckBox().getValue()) {
								contact.setOtheraddress(contact.getPrimaddress());
								contact.setOthercity(contact.getPrimcity());
								contact.setOtherstate(contact.getPrimstate());
								contact.setOtherpostalcode(contact.getPrimpostalcode());
								contact.setOthercountry(contact.getPrimcountry());
							} else {
								contact.setOtheraddress("");
								contact.setOthercity("");
								contact.setOtherstate("");
								contact.setOtherpostalcode("");
								contact.setOthercountry("");
							}
							
							isCheckToCopy = (Boolean) chkField.getCheckBox().getValue();
							editForm.setItemDataSource(new BeanItem<Contact>(contact));
						}
                		
                	});
                	
                	chkField.getCheckBox().setValue(isCheckToCopy);
                	
                	return chkField;
                }


                return null;
            }
        }
    }
}
