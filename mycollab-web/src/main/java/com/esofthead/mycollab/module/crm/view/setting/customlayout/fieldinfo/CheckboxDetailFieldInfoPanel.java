package com.esofthead.mycollab.module.crm.view.setting.customlayout.fieldinfo;

import java.util.List;

import com.esofthead.mycollab.form.view.builder.type.BooleanDynaField;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;

public class CheckboxDetailFieldInfoPanel extends
		DetailFieldInfoPanel<BooleanDynaField> {
	private static final long serialVersionUID = 1L;

	public CheckboxDetailFieldInfoPanel(String candidateFieldName,
			List<DynaSection> activeSections) {
		super(candidateFieldName, activeSections);
	}

	@Override
	public DynaSection updateCustomField() {
		return null;
	}

}
