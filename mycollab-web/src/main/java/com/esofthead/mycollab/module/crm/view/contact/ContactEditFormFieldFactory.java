package com.esofthead.mycollab.module.crm.view.contact;

import java.util.Date;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
import com.esofthead.mycollab.module.crm.view.lead.LeadSourceComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.DateComboboxSelectionField;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.vaadin.data.Item;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ContactEditFormFieldFactory extends DefaultEditFormFieldFactory {
	private static final long serialVersionUID = 1L;

	private Contact contact;

	public ContactEditFormFieldFactory(Contact contact) {
		this.contact = contact;
	}

	@Override
	protected Field onCreateField(Item item, Object propertyId,
			com.vaadin.ui.Component uiContext) {

		if (propertyId.equals("leadsource")) {
			LeadSourceComboBox leadSource = new LeadSourceComboBox();
			return leadSource;
		} else if (propertyId.equals("accountid")) {
			AccountSelectionField contactField = new AccountSelectionField();
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
		} else if (propertyId.equals("birthday")) {
			ContactBirthdayField birthdayField = new ContactBirthdayField();
			if (contact.getBirthday() != null) {
				birthdayField.setDate(contact.getBirthday());
			}
			return birthdayField;
		}
		return null;
	}

	private class ContactBirthdayField extends CustomField implements
			FieldSelection {
		private static final long serialVersionUID = 1L;
		private final DateComboboxSelectionField dateSelection;

		public ContactBirthdayField() {
			this.dateSelection = new DateComboboxSelectionField(this);

			this.setCompositionRoot(this.dateSelection);
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		public Object getValue() {
			return dateSelection.getDate();
		}

		public void setDate(Date date) {
			dateSelection.setDate(date);
		}

		@Override
		public void fireValueChange(Object data) {
			contact.setBirthday((Date) data);

		}
	}
}
