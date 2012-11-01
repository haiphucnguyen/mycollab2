package com.esofthead.mycollab.module.crm.ui.components;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.data.DataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

@Component
public class AccountIndustryComboBox extends ValueComboBox {

	private static final long serialVersionUID = 1L;

	public AccountIndustryComboBox() {
		super();
		setCaption(null);
		loadData(DataTypeFactory.getAccountIndustryList());
	}
}
