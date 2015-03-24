package com.esofthead.mycollab.premium.module.project.view.risk;

import java.util.Arrays;
import java.util.Collection;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.CustomizedTableWindow;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class RiskListCustomizeWindow extends CustomizedTableWindow {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
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
