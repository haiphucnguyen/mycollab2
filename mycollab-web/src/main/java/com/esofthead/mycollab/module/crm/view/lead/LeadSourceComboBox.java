package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

@SuppressWarnings("serial")
public class LeadSourceComboBox extends ValueComboBox {

	public LeadSourceComboBox() {
		this.loadData(CrmDataTypeFactory.getLeadSourceList());
	}
}
