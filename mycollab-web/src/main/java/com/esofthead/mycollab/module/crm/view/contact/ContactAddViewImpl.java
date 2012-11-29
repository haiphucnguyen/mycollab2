package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
import com.esofthead.mycollab.module.crm.view.lead.LeadSourceComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.FormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public class ContactAddViewImpl extends FormAddView<Contact> implements
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
	protected void onNewItem() {
		this.contact = new Contact();
		editForm.setItemDataSource(new BeanItem<Contact>(contact));
	}

	@Override
	protected void onEditItem(Contact item) {
		this.contact = item;
		editForm.setItemDataSource(new BeanItem<Contact>(contact));

	}

	@Override
	public HasEditFormHandlers<Contact> getEditFormHandlers() {
		return editForm;
	}

	public class EditForm extends AdvancedEditBeanForm<Contact> {
		private static final long serialVersionUID = 1L;

		public EditForm() {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
		}

		class FormLayoutFactory extends ContactFormLayoutFactory {

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
				}

				return null;
			}
		}
	}

}
