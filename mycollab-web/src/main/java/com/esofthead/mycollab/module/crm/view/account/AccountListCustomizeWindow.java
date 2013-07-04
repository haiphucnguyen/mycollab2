package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.vaadin.ui.Window;

public class AccountListCustomizeWindow extends Window {
	private static final long serialVersionUID = 1L;

	private AbstractPagedBeanTable table;

	public AccountListCustomizeWindow(AbstractPagedBeanTable table) {
		this.table = table;
	}
	
	
}
