package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface CrmCustomView extends View {
	void display(String moduleName);

	void addActiveSection(DynaSection section);
}
