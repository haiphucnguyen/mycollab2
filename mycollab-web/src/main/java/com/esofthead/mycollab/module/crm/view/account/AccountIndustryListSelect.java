package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.view.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueListSelect;

@SuppressWarnings("serial")
public class AccountIndustryListSelect extends ValueListSelect {
	public AccountIndustryListSelect() {
		super();
		setCaption(null);
		loadData(CrmDataTypeFactory.getAccountIndustryList());
	}
}
