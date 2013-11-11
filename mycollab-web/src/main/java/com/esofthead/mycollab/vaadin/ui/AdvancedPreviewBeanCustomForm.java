package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.vaadin.data.Item;
import com.vaadin.ui.Field;

public abstract class AdvancedPreviewBeanCustomForm<T> extends
		AdvancedPreviewBeanForm<T> {
	private static final long serialVersionUID = 1L;

	protected DynaFormLayout formLayout;

	public AdvancedPreviewBeanCustomForm(DynaFormLayout formLayout) {
		this.formLayout = formLayout;

		this.setFormLayoutFactory(formLayout);
		this.setFormFieldFactory(new DefaultCustomFormViewFieldFactory(
				formLayout) {
			private static final long serialVersionUID = 1L;

			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {
				return AdvancedPreviewBeanCustomForm.this.onCreateField(item,
						propertyId, uiContext);
			}
		});
	}

	abstract protected Field onCreateField(final Item item,
			final Object propertyId, final com.vaadin.ui.Component uiContext);
}
