package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueListSelect;

public class CasePriorityListSelect extends ValueListSelect{
	private static final long serialVersionUID = 1L;

	public CasePriorityListSelect() {
		super();
		setCaption(null);
		this.loadData(CrmDataTypeFactory.getCasesPriorityList());
	}
}
