package com.esofthead.mycollab.vaadin.ui.form.field;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.3
 *
 */
@SuppressWarnings("rawtypes")
public class ContainerHorizontalViewField extends CustomField {
	private static final long serialVersionUID = 1L;

	private HorizontalLayout layout;

	public ContainerHorizontalViewField() {
		layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);
	}

	public void addComponentField(final Component component) {
		layout.addComponent(component);
	}

	public HorizontalLayout getLayout() {
		return layout;
	}

	@Override
	public Class<?> getType() {
		return Object.class;
	}

	@Override
	protected Component initContent() {
		return layout;
	}
}
