package com.esofthead.mycollab.form.view.builder;

import com.esofthead.mycollab.form.view.builder.type.StringDynaField;

public class StringDynaFieldBuilder extends AbstractDynaFieldBuilder<StringDynaField> {
	public StringDynaFieldBuilder() {
		field = new StringDynaField();
	}

	public StringDynaFieldBuilder maxLength(int maxLength) {
		field.setMaxLength(maxLength);
		return this;
	}
}
