package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;

public class LeadDefaultDynaFormLayoutFactory {
	public static final DynaForm defaultForm;

	static {
		defaultForm = new DynaForm();

		DynaSection infoSection = new DynaSectionBuilder()
				.layoutType(LayoutType.TWO_COLUMN).orderIndex(0)
				.header("Contact Information").build();

		defaultForm.addSection(infoSection);
	}

	public static DynaForm getForm() {
		return defaultForm;
	}
}
