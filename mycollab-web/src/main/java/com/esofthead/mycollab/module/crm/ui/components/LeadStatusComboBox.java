package com.esofthead.mycollab.module.crm.ui.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class LeadStatusComboBox extends ValueComboBox {
	public LeadStatusComboBox() {
		this.loadData(CrmDataTypeFactory.getLeadStatusList());
	}
}
