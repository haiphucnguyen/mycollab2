package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.view.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

public class CaseTypeComboBox extends ValueComboBox{
	private static final long serialVersionUID = 1L;

	public CaseTypeComboBox() {
		super();
		setCaption(null);
		this.loadData(CrmDataTypeFactory.getCasesType());
	}
}
