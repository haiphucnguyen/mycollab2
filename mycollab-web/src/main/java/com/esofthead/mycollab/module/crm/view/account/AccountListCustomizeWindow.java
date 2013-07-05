package com.esofthead.mycollab.module.crm.view.account;

import java.util.Arrays;
import java.util.Collection;

import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.CustomizedTableWindow;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public class AccountListCustomizeWindow extends CustomizedTableWindow {
	private static final long serialVersionUID = 1L;

	public AccountListCustomizeWindow(AbstractPagedBeanTable table) {
		super(table);
	}

	protected Collection<TableViewField> getAvailableColumns() {
		return Arrays.asList(AccountTableFieldDef.accountname,
				AccountTableFieldDef.assignUser, AccountTableFieldDef.city,
				AccountTableFieldDef.email, AccountTableFieldDef.phoneoffice,
				AccountTableFieldDef.website, AccountTableFieldDef.type,
				AccountTableFieldDef.ownership, AccountTableFieldDef.fax);
	}

	@Override
	protected String getViewId() {
		return AccountListView.VIEW_DEF_ID;
	}
}
