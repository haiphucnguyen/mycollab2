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
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.ui.components.IndustryComboBox;
import com.esofthead.mycollab.module.user.ui.components.ActiveUserComboBox;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.vaadin.data.Item;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class AccountEditFormFieldFactory extends DefaultEditFormFieldFactory {

	private static final long serialVersionUID = 1L;

	private Account account;

	public AccountEditFormFieldFactory(Account account) {
		this.account = account;
	}

	@Override
	protected Field onCreateField(Item item, Object propertyId,
			com.vaadin.ui.Component uiContext) {

		if ("type".equals(propertyId)) {
			AccountTypeComboBox accountTypeBox = new AccountTypeComboBox();
			return accountTypeBox;
		} else if ("industry".equals(propertyId)) {
			IndustryComboBox accountIndustryBox = new IndustryComboBox();
			return accountIndustryBox;
		} else if ("assignuser".equals(propertyId)) {
			ActiveUserComboBox userBox = new ActiveUserComboBox();
			userBox.select(account.getAssignuser());
			return userBox;
		} else if ("description".equals(propertyId)) {
			TextArea textArea = new TextArea("", "");
			textArea.setNullRepresentation("");
			return textArea;
		} else if ("billingcountry".equals(propertyId)
				|| "shippingcountry".equals(propertyId)) {
			CountryComboBox billingCountryComboBox = new CountryComboBox();
			return billingCountryComboBox;
		} else if (propertyId.equals("accountname")) {
			TextField tf = new TextField();
			tf.setNullRepresentation("");
			tf.setRequired(true);
			tf.setRequiredError("Please enter an Account Name");
			return tf;
		}

		return null;
	}

}
