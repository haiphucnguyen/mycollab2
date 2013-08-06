package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.view.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueListSelect;

@SuppressWarnings("serial")
public class LeadStatusListSelect extends ValueListSelect {
	public LeadStatusListSelect() {
		this.loadData(CrmDataTypeFactory.getLeadStatusList());
	}
}
