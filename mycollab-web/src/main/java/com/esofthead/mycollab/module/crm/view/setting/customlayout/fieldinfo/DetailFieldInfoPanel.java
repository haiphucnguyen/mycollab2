package com.esofthead.mycollab.module.crm.view.setting.customlayout.fieldinfo;

import java.util.List;

import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.vaadin.ui.VerticalLayout;

public abstract class DetailFieldInfoPanel<F extends AbstractDynaField> extends
		VerticalLayout {
	private static final long serialVersionUID = 1L;

	protected List<DynaSection> activeSections;
	protected String candidateFieldName;
	protected F field;

	public DetailFieldInfoPanel(String candidateFieldName,
			List<DynaSection> activeSections) {
		this.activeSections = activeSections;
		this.candidateFieldName = candidateFieldName;
	}

	public abstract DynaSection updateCustomField();
}
