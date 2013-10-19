package com.esofthead.mycollab.form.view.builder;

import com.esofthead.mycollab.form.view.builder.type.PickListDynaField;

public class PickListFieldBuilder<T> extends
		AbstractFieldBuilder<PickListDynaField<T>> {

	public PickListFieldBuilder() {
		field = new PickListDynaField<T>();
	}

	public void options(T... options) {
		for (T option : options) {
			field.addOption(option);
		}
	}
}
