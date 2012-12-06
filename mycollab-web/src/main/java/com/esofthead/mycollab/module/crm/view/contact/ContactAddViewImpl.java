package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
import com.esofthead.mycollab.module.crm.view.lead.LeadSourceComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ContactAddViewImpl extends AbstractView implements
		IFormAddView<Contact>, ContactAddView {
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
		editForm.setItemDataSource(new BeanItem<Contact>(contact));

	}

	@Override
	public HasEditFormHandlers<Contact> getEditFormHandlers() {
		return editForm;
	}

	private class EditForm extends AdvancedEditBeanForm<Contact> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends ContactFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			@Override
			protected Layout createButtonControls() {
				return (new EditFormControlsGenerator<Contact>(EditForm.this))
						.createButtonControls();
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
				} else if (propertyId.equals("accountid")) {
					AccountSelectionField accountField = new AccountSelectionField();
					if (contact.getAccountid() != null) {
						AccountService accountService = AppContext
								.getSpringBean(AccountService.class);
						SimpleAccount account = accountService
								.findAccountById(contact.getAccountid());
						if (account != null) {
							accountField.setAccount(account);
						}
					}
					return accountField;
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
				}

				return null;
			}
		}
	}

}
