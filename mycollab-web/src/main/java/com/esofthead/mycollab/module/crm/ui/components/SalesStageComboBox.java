package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

@SuppressWarnings("serial")
public class SalesStageComboBox extends ValueComboBox {
	public SalesStageComboBox() {
		this.loadData(CrmDataTypeFactory.getSalesStageList());
	}
}
