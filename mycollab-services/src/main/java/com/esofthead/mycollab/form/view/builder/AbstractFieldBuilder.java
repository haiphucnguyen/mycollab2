package com.esofthead.mycollab.form.view.builder;

import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;

public abstract class AbstractFieldBuilder<F extends AbstractDynaField> {
	protected F field;

	public AbstractFieldBuilder<F> fieldIndex(int index) {
		field.setFieldIndex(index);
		return this;
	}

	public AbstractFieldBuilder<F> fieldName(String fieldName) {
		field.setFieldName(fieldName);
		return this;
	}

	public AbstractFieldBuilder<F> displayName(String displayName) {
		field.setDisplayName(displayName);
		return this;
	}

	public AbstractFieldBuilder<F> required(boolean isRequired) {
		field.setRequired(isRequired);
		return this;
	}

	public AbstractDynaField build() {
		return field;
	}
}
