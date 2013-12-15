/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.contact;

import java.util.Date;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
import com.esofthead.mycollab.module.crm.view.lead.LeadSourceComboBox;
import com.esofthead.mycollab.module.user.ui.components.ActiveUserComboBox;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.DateComboboxSelectionField;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

class ContactEditFormFieldFactory<B extends Contact> extends
		AbstractBeanFieldGroupEditFieldFactory<B> {
	private static final long serialVersionUID = 1L;

	ContactEditFormFieldFactory(GenericBeanForm<B> form) {
		super(form);
	}

	@Override
	protected Field onCreateField(Object propertyId) {

		if (propertyId.equals("leadsource")) {
			LeadSourceComboBox leadSource = new LeadSourceComboBox();
			return leadSource;
		} else if (propertyId.equals("accountid")) {
			AccountSelectionField accountField = new AccountSelectionField();
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
			ActiveUserComboBox userBox = new ActiveUserComboBox();
			userBox.select(attachForm.getBean().getAssignuser());
			return userBox;
		} else if (propertyId.equals("primcountry")
				|| propertyId.equals("othercountry")) {
			CountryComboBox otherCountryComboBox = new CountryComboBox();
			return otherCountryComboBox;
		} else if (propertyId.equals("birthday")) {
			ContactBirthdayField birthdayField = new ContactBirthdayField();
			if (attachForm.getBean().getBirthday() != null) {
				birthdayField.setDate(attachForm.getBean().getBirthday());
			}
			return birthdayField;
		}
		return null;
	}

	private class ContactBirthdayField extends CustomField<Date> implements
			FieldSelection {
		private static final long serialVersionUID = 1L;
		private DateComboboxSelectionField dateSelection;

		@Override
		public Class<Date> getType() {
			return Date.class;
		}

		public Date getValue() {
			return dateSelection.getDate();
		}

		public void setDate(Date date) {
			dateSelection.setDate(date);
		}

		@Override
		public void fireValueChange(Object data) {
			attachForm.getBean().setBirthday((Date) data);

		}

		@Override
		protected Component initContent() {
			this.dateSelection = new DateComboboxSelectionField(this);
			return dateSelection;
		}
	}
}
