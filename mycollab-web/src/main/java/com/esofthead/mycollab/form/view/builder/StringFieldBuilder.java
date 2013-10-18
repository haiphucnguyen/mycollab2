package com.esofthead.mycollab.form.view.builder;

import com.esofthead.mycollab.form.view.builder.type.StringDynaField;

public class StringFieldBuilder extends AbstractFieldBuilder<StringDynaField> {
	public StringFieldBuilder() {
		field = new StringDynaField();
	}

	public StringFieldBuilder maxLength(int maxLength) {
		field.setMaxLength(maxLength);
		return this;
	}
}
