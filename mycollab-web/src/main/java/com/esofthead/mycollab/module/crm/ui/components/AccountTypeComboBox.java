package com.esofthead.mycollab.module.crm.ui.components;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.data.DataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

@Component
public class AccountTypeComboBox extends ValueComboBox {
	private static final long serialVersionUID = 1L;

	public AccountTypeComboBox() {
		super();
		setCaption(null);
		this.loadData(DataTypeFactory.getAccountTypeList());
	}

}
