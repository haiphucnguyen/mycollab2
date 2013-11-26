package com.esofthead.mycollab.module.crm.view.setting;

import java.util.List;

import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface ICrmCustomView extends View {
	void display(String moduleName);

	void addActiveSection(DynaSection section);

	List<DynaSection> getActiveSections();

	String getCandidateTextFieldName();

	void refreshSectionLayout(DynaSection section);
}
