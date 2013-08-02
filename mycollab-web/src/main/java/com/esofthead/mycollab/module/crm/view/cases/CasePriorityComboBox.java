package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

public class CasePriorityComboBox extends ValueComboBox {
	private static final long serialVersionUID = 1L;

	public CasePriorityComboBox() {
		super();
		setCaption(null);
		this.loadData(CrmDataTypeFactory.getCasesPriorityList());
	}
}
