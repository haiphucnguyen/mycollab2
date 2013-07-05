package com.esofthead.mycollab.module.crm.view.lead;

import java.util.Arrays;
import java.util.Collection;

import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.CustomizedTableWindow;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public class LeadListCustomizeWindow extends CustomizedTableWindow {
	private static final long serialVersionUID = 1L;

	public LeadListCustomizeWindow(AbstractPagedBeanTable table) {
		super(table);
	}

	@Override
	protected Collection<TableViewField> getAvailableColumns() {
		return Arrays.asList(LeadTableFieldDef.accountName,
				LeadTableFieldDef.assignedUser, LeadTableFieldDef.department,
				LeadTableFieldDef.email, LeadTableFieldDef.fax,
				LeadTableFieldDef.industry, LeadTableFieldDef.leadSource,
				LeadTableFieldDef.mobile, LeadTableFieldDef.name,
				LeadTableFieldDef.status, LeadTableFieldDef.title,
				LeadTableFieldDef.website);
	}

	@Override
	protected String getViewId() {
		return LeadListView.VIEW_DEF_ID;
	}

}
