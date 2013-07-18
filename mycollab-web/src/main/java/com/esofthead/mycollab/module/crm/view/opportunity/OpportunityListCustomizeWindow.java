package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.Arrays;
import java.util.Collection;

import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.CustomizedTableWindow;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public class OpportunityListCustomizeWindow extends CustomizedTableWindow {
	private static final long serialVersionUID = 1L;

	public OpportunityListCustomizeWindow(String viewId,
			AbstractPagedBeanTable table) {
		super(viewId, table);
	}

	@Override
	protected Collection<TableViewField> getAvailableColumns() {
		return Arrays.asList(OpportunityTableFieldDef.accountName,
				OpportunityTableFieldDef.amount,
				OpportunityTableFieldDef.assignUser,
				OpportunityTableFieldDef.campaignName,
				OpportunityTableFieldDef.currency,
				OpportunityTableFieldDef.expectedCloseDate,
				OpportunityTableFieldDef.leadSource,
				OpportunityTableFieldDef.opportunityName,
				OpportunityTableFieldDef.probability,
				OpportunityTableFieldDef.type);
	}

}
