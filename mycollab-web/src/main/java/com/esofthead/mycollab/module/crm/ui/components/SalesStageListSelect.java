package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueListSelect;

@SuppressWarnings("serial")
public class SalesStageListSelect extends ValueListSelect {
	public SalesStageListSelect() {
		this.loadData(CrmDataTypeFactory.getSalesStageList());
	}
}
