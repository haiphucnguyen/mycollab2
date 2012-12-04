package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

public class TaskStatusComboBox extends ValueComboBox {
	private static final long serialVersionUID = 1L;

	public TaskStatusComboBox() {
		super();
		setCaption(null);
		this.loadData(CrmDataTypeFactory.getTaskStatuses());
	}
}
