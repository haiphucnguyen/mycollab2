package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.ui.components.AccountSelectionField;
import com.esofthead.mycollab.module.crm.ui.components.LeadSourceComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public class ContactAddViewImpl extends AbstractView implements ContactAddView {
	private static final long serialVersionUID = 1L;
	private EditForm editForm;

	public ContactAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void addNewItem() {
		editForm.setItemDataSource(new BeanItem<Contact>(new Contact()));

	}

	@Override
	public void editItem(Contact item) {
		editForm.setItemDataSource(new BeanItem<Contact>(item));

	}

	@Override
	public HasEditFormHandlers<Contact> getEditFormHandlers() {
		return editForm;
	}

	public static class EditForm extends AdvancedEditBeanForm<Contact> {
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
					return accountField;
				}

				return null;
			}
		}
	}

}
