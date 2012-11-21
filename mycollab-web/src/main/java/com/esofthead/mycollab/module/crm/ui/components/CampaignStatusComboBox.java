package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

@SuppressWarnings("serial")
public class CampaignStatusComboBox extends ValueComboBox {
	public CampaignStatusComboBox() {
		this.loadData(CrmDataTypeFactory.getCampaignStatusList());
	}
}
