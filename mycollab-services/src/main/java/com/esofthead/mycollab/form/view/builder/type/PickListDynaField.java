package com.esofthead.mycollab.form.view.builder.type;

import java.util.List;

public class PickListDynaField<T> extends AbstractDynaField {

	private List<T> options;

	public void addOption(T option) {
		options.add(option);
	}
}
