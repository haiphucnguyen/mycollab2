package com.esofthead.mycollab.form.view.builder;

import com.esofthead.mycollab.form.view.builder.type.PickListDynaField;

public class PickListDynaFieldBuilder<T> extends
		AbstractDynaFieldBuilder<PickListDynaField<T>> {

	public PickListDynaFieldBuilder() {
		field = new PickListDynaField<T>();
	}

	public void options(T... options) {
		for (T option : options) {
			field.addOption(option);
		}
	}
}
