package com.esofthead.mycollab.premium.module.project.view.problem;

import java.util.Arrays;
import java.util.Collection;

import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.CustomizedTableWindow;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public class ProblemListCustomizeWindow extends CustomizedTableWindow {
	private static final long serialVersionUID = 1L;

	public ProblemListCustomizeWindow(String viewId,
			AbstractPagedBeanTable table) {
		super(viewId, table);
	}

	@Override
	protected Collection<TableViewField> getAvailableColumns() {
		return Arrays.asList(ProblemTableFieldDef.assignUser,
				ProblemTableFieldDef.datedue, ProblemTableFieldDef.description,
				ProblemTableFieldDef.impact, ProblemTableFieldDef.name,
				ProblemTableFieldDef.priority, ProblemTableFieldDef.raisedby,
				ProblemTableFieldDef.rating, ProblemTableFieldDef.status);
	}

}
