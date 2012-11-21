package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

public class AccountTypeComboBox extends ValueComboBox {
	private static final long serialVersionUID = 1L;

	public AccountTypeComboBox() {
		super();
		setCaption(null);
		this.loadData(CrmDataTypeFactory.getAccountTypeList());
	}

}
