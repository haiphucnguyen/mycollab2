package com.esofthead.mycollab.module.project.view.bug;

import java.util.Arrays;
import java.util.Collection;

import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.CustomizedTableWindow;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public class BugListCustomizeWindow extends CustomizedTableWindow {
	private static final long serialVersionUID = 1L;

	public BugListCustomizeWindow(AbstractPagedBeanTable table) {
		super(table);
	}

	@Override
	protected Collection<TableViewField> getAvailableColumns() {
		return Arrays.asList(BugTableFieldDef.assignUser,
				BugTableFieldDef.createdTime, BugTableFieldDef.description,
				BugTableFieldDef.duedate, BugTableFieldDef.environment,
				BugTableFieldDef.logBy, BugTableFieldDef.priority,
				BugTableFieldDef.resolution, BugTableFieldDef.status,
				BugTableFieldDef.summary);
	}

	@Override
	protected String getViewId() {
		return BugListView.VIEW_DEF_ID;
	}

}
