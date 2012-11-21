package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueListSelect;

@SuppressWarnings("serial")
public class AccountIndustryListSelect extends ValueListSelect {
	public AccountIndustryListSelect() {
		super();
		setCaption(null);
		loadData(CrmDataTypeFactory.getAccountIndustryList());
	}
}
