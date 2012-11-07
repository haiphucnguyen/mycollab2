package com.esofthead.mycollab.module.crm.ui.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.data.DataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueListSelect;

@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class SalesStageListSelect extends ValueListSelect {
	public SalesStageListSelect() {
		this.loadData(DataTypeFactory.getSalesStageList());
	}
}
