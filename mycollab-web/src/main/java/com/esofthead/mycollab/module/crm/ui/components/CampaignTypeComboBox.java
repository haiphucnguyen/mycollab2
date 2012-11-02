package com.esofthead.mycollab.module.crm.ui.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.data.DataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class CampaignTypeComboBox extends ValueComboBox {
	public CampaignTypeComboBox() {
		this.loadData(DataTypeFactory.getCampaignTypeList());
	}
}
