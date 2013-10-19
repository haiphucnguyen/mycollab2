package com.esofthead.mycollab.form.view.builder;

import com.esofthead.mycollab.form.view.builder.type.DynaForm;

public class DynaFormBuilder {

	private DynaForm form;

	public DynaFormBuilder() {
		form = new DynaForm();
	}

	public DynaFormBuilder sections(DynaSectionBuilder... sections) {
		for (DynaSectionBuilder section : sections) {
			form.addSection(section.build());
		}
		return this;
	}

	public DynaForm build() {
		return form;
	}
}
