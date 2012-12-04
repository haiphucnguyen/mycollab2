package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

public class TaskPriorityComboBox extends ValueComboBox {
	private static final long serialVersionUID = 1L;

	public TaskPriorityComboBox() {
		super();
		setCaption(null);
		this.loadData(CrmDataTypeFactory.getTaskPriorities());
	}

}
