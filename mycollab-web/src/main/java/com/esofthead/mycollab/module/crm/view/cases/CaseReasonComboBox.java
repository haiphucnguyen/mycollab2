package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

public class CaseReasonComboBox extends ValueComboBox {
	private static final long serialVersionUID = 1L;

	public CaseReasonComboBox() {
		super();
		setCaption(null);
		this.loadData(CrmDataTypeFactory.getCasesReason());
	}
}
