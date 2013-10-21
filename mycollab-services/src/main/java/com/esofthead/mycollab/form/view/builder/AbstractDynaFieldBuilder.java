package com.esofthead.mycollab.form.view.builder;

import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;

public abstract class AbstractDynaFieldBuilder<F extends AbstractDynaField> {
	protected F field;

	public AbstractDynaFieldBuilder<F> fieldIndex(int index) {
		field.setFieldIndex(index);
		return this;
	}

	public AbstractDynaFieldBuilder<F> fieldName(String fieldName) {
		field.setFieldName(fieldName);
		return this;
	}

	public AbstractDynaFieldBuilder<F> displayName(String displayName) {
		field.setDisplayName(displayName);
		return this;
	}

	public AbstractDynaFieldBuilder<F> required(boolean isRequired) {
		field.setRequired(isRequired);
		return this;
	}

	public AbstractDynaFieldBuilder<F> customField(boolean isCustom) {
		field.setCustom(isCustom);
		return this;
	}

	public AbstractDynaField build() {
		return field;
	}
}
