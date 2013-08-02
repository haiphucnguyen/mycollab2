package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueListSelect;

public class LeadSourceListSelect extends ValueListSelect {
	private static final long serialVersionUID = 1L;

	public LeadSourceListSelect() {
		this.loadData(CrmDataTypeFactory.getLeadSourceList());
	}
}
