package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

@SuppressWarnings("serial")
public class CampaignTypeComboBox extends ValueComboBox {
	public CampaignTypeComboBox() {
		this.loadData(CrmDataTypeFactory.getCampaignTypeList());
	}
}
