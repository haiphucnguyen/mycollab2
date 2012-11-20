package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

public class IndustryComboBox extends ValueComboBox {

	private static final long serialVersionUID = 1L;

	public IndustryComboBox() {
		super();
		setCaption(null);
		loadData(CrmDataTypeFactory.getAccountIndustryList());
	}
}
