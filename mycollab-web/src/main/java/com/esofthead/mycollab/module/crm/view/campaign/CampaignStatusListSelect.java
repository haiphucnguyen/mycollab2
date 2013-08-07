package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.view.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueListSelect;

@SuppressWarnings("serial")
public class CampaignStatusListSelect extends ValueListSelect {
	public CampaignStatusListSelect() {
		this.loadData(CrmDataTypeFactory.getCampaignStatusList());
	}
}
