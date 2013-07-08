package com.esofthead.mycollab.module.crm.view.contact;

import java.util.Arrays;
import java.util.Collection;

import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.CustomizedTableWindow;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public class ContactListCustomizeWindow extends CustomizedTableWindow {
	private static final long serialVersionUID = 1L;

	public ContactListCustomizeWindow(AbstractPagedBeanTable table) {
		super(table);
	}

	@Override
	protected Collection<TableViewField> getAvailableColumns() {
		return Arrays.asList(ContactTableFieldDef.account,
				ContactTableFieldDef.assignUser,
				ContactTableFieldDef.assistant,
				ContactTableFieldDef.assistantPhone,
				ContactTableFieldDef.birthday, ContactTableFieldDef.department,
				ContactTableFieldDef.email, ContactTableFieldDef.fax,
				ContactTableFieldDef.isCallable, ContactTableFieldDef.mobile,
				ContactTableFieldDef.name, ContactTableFieldDef.phoneOffice,
				ContactTableFieldDef.title);
	}

	@Override
	protected String getViewId() {
		return ContactListView.VIEW_DEF_ID;
	}

}
