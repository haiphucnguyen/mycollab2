package com.esofthead.mycollab.form.view.builder;

import com.esofthead.mycollab.form.view.builder.type.IntDynaField;

public class IntFieldBuilder extends AbstractFieldBuilder<IntDynaField> {
	public IntFieldBuilder() {
		field = new IntDynaField();
	}

	public IntFieldBuilder maxValue(int maxValue) {
		field.setMaxValue(maxValue);
		return this;
	}

}
