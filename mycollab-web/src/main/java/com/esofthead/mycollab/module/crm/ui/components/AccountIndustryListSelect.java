package com.esofthead.mycollab.module.crm.ui.components;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.data.DataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueListSelect;

@SuppressWarnings("serial")
@Component
public class AccountIndustryListSelect extends ValueListSelect {
	public AccountIndustryListSelect() {
		super();
		setCaption(null);
		loadData(DataTypeFactory.getAccountIndustryList());
	}
}
