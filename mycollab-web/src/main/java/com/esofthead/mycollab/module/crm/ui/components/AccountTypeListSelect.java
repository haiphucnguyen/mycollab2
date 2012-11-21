package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueListSelect;

public class AccountTypeListSelect  extends ValueListSelect {
	private static final long serialVersionUID = 1L;

	public AccountTypeListSelect() {
		super();
		setCaption(null);
		this.loadData(CrmDataTypeFactory.getAccountTypeList());
	}
}
