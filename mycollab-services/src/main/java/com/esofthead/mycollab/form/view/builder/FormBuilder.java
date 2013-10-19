package com.esofthead.mycollab.form.view.builder;

import com.esofthead.mycollab.form.view.builder.type.DynaForm;

public class FormBuilder {

	private DynaForm form;

	public FormBuilder() {
		form = new DynaForm();
	}

	public FormBuilder sections(SectionBuilder... sections) {
		for (SectionBuilder section : sections) {
			form.addSection(section.build());
		}
		return this;
	}

	public DynaForm build() {
		return form;
	}
}
