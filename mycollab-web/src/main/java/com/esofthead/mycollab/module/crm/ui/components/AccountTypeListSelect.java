package com.esofthead.mycollab.module.crm.ui.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.data.DataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueListSelect;

@Scope("prototype")
@Component
public class AccountTypeListSelect  extends ValueListSelect {
	private static final long serialVersionUID = 1L;

	public AccountTypeListSelect() {
		super();
		setCaption(null);
		this.loadData(DataTypeFactory.getAccountTypeList());
	}
}
