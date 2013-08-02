package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

@SuppressWarnings("serial")
public class LeadStatusComboBox extends ValueComboBox {
	public LeadStatusComboBox() {
		this.loadData(CrmDataTypeFactory.getLeadStatusList());
	}
}
