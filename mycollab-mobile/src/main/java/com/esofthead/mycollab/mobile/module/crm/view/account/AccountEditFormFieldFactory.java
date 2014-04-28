package com.esofthead.mycollab.mobile.module.crm.view.account;

import com.esofthead.mycollab.mobile.module.user.ui.components.ActiveUserComboBox;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IndustryComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class AccountEditFormFieldFactory<B extends Account> extends
		AbstractBeanFieldGroupEditFieldFactory<B> {

	private static final long serialVersionUID = 1L;

	public AccountEditFormFieldFactory(GenericBeanForm<B> form) {
		super(form);
	}

	AccountEditFormFieldFactory(GenericBeanForm<B> form, boolean isValidateForm) {
		super(form, isValidateForm);
	}

	@Override
	protected Field<?> onCreateField(Object propertyId) {
		if ("type".equals(propertyId)) {
			AccountTypeComboBox accountTypeBox = new AccountTypeComboBox();
			return accountTypeBox;
		} else if ("industry".equals(propertyId)) {
			IndustryComboBox accountIndustryBox = new IndustryComboBox();
			return accountIndustryBox;
		} else if ("assignuser".equals(propertyId)) {
			ActiveUserComboBox userBox = new ActiveUserComboBox();
			userBox.select(attachForm.getBean().getAssignuser());
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
			if (isValidateForm) {
				tf.setNullRepresentation("");
				tf.setRequired(true);
				tf.setRequiredError("Please enter an Account Name");
			}

			return tf;
		}

		return null;
	}

}
