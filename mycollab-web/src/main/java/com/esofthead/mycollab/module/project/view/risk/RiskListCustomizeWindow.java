package com.esofthead.mycollab.module.project.view.risk;

import java.util.Arrays;
import java.util.Collection;

import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.CustomizedTableWindow;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public class RiskListCustomizeWindow extends CustomizedTableWindow {
	private static final long serialVersionUID = 1L;

	public RiskListCustomizeWindow(String viewId, AbstractPagedBeanTable table) {
		super(viewId, table);
	}

	@Override
	protected Collection<TableViewField> getAvailableColumns() {
		return Arrays.asList(RiskTableFieldDef.assignUser,
				RiskTableFieldDef.consequence, RiskTableFieldDef.datedue,
				RiskTableFieldDef.description, RiskTableFieldDef.name,
				RiskTableFieldDef.probability, RiskTableFieldDef.raisedBy,
				RiskTableFieldDef.rating, RiskTableFieldDef.response,
				RiskTableFieldDef.status);
	}

}
