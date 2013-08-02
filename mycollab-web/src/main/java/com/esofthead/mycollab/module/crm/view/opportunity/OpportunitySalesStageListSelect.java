package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueListSelect;

@SuppressWarnings("serial")
public class OpportunitySalesStageListSelect extends ValueListSelect {
	public OpportunitySalesStageListSelect() {
		this.loadData(CrmDataTypeFactory.getOpportunitySalesStageList());
	}
}
