package com.esofthead.mycollab.form.view.builder;

import com.esofthead.mycollab.form.view.builder.type.TextDynaField;

public class TextDynaFieldBuilder extends AbstractDynaFieldBuilder<TextDynaField> {
	public TextDynaFieldBuilder() {
		field = new TextDynaField();
	}

	public TextDynaFieldBuilder maxLength(int maxLength) {
		field.setMaxLength(maxLength);
		return this;
	}
}
