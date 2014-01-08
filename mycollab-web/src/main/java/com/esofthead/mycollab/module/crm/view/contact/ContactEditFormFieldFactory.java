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

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
import com.esofthead.mycollab.module.crm.view.lead.LeadSourceComboBox;
import com.esofthead.mycollab.module.user.ui.components.ActiveUserComboBox;
import com.esofthead.mycollab.vaadin.resource.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.resource.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.resource.ui.DateComboboxSelectionField;
import com.esofthead.mycollab.vaadin.resource.ui.GenericBeanForm;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 * @param <B>
 */
class ContactEditFormFieldFactory<B extends Contact> extends
		AbstractBeanFieldGroupEditFieldFactory<B> {
	private static final long serialVersionUID = 1L;

	ContactEditFormFieldFactory(GenericBeanForm<B> form) {
		super(form);
	}

	@Override
	protected Field<?> onCreateField(Object propertyId) {

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
			return new DateComboboxSelectionField();
		}
		return null;
	}
}
