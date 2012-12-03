package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

public class OpportunityTypeComboBox extends ValueComboBox {
	private static final long serialVersionUID = 1L;

	public OpportunityTypeComboBox() {
		this.loadData(CrmDataTypeFactory.getOpportunityTypeList());
	}

}
