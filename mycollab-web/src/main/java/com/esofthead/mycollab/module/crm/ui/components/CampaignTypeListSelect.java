package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueListSelect;

@SuppressWarnings("serial")
public class CampaignTypeListSelect extends ValueListSelect {
	public CampaignTypeListSelect() {
		this.loadData(CrmDataTypeFactory.getCampaignTypeList());
	}
}
