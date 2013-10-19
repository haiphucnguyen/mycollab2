package com.esofthead.mycollab.form.view.builder;

import com.esofthead.mycollab.form.view.builder.type.IntDynaField;

public class IntDynaFieldBuilder extends AbstractDynaFieldBuilder<IntDynaField> {
	public IntDynaFieldBuilder() {
		field = new IntDynaField();
	}

	public IntDynaFieldBuilder maxValue(int maxValue) {
		field.setMaxValue(maxValue);
		return this;
	}

}
