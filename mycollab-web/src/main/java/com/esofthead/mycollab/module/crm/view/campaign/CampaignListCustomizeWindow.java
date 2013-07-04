package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.Arrays;
import java.util.Collection;

import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.CustomizedTableWindow;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public class CampaignListCustomizeWindow extends CustomizedTableWindow {
	private static final long serialVersionUID = 1L;

	public CampaignListCustomizeWindow(AbstractPagedBeanTable table) {
		super(table);
	}

	@Override
	protected Collection<TableViewField> getAvailableColumns() {
		return Arrays.asList(CampaignTableFieldDef.assignUser,
				CampaignTableFieldDef.campaignname,
				CampaignTableFieldDef.endDate,
				CampaignTableFieldDef.expectedRevenue,
				CampaignTableFieldDef.startDate, CampaignTableFieldDef.status,
				CampaignTableFieldDef.type);
	}

	@Override
	protected String getViewId() {
		return CampaignListView.VIEW_DEF_ID;
	}

}
