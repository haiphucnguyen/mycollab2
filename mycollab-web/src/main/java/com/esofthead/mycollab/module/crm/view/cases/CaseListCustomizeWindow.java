package com.esofthead.mycollab.module.crm.view.cases;

import java.util.Arrays;
import java.util.Collection;

import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.CustomizedTableWindow;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public class CaseListCustomizeWindow extends CustomizedTableWindow {
	private static final long serialVersionUID = 1L;

	public CaseListCustomizeWindow(String viewId, AbstractPagedBeanTable table) {
		super(viewId, table);
	}

	@Override
	protected Collection<TableViewField> getAvailableColumns() {
		return Arrays.asList(CaseTableFieldDef.account,
				CaseTableFieldDef.assignUser, CaseTableFieldDef.createdTime,
				CaseTableFieldDef.email, CaseTableFieldDef.origin,
				CaseTableFieldDef.lastUpdatedTime, CaseTableFieldDef.phone,
				CaseTableFieldDef.priority, CaseTableFieldDef.reason,
				CaseTableFieldDef.status, CaseTableFieldDef.subject,
				CaseTableFieldDef.type);
	}

}
