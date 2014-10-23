package com.esofthead.mycollab.vaadin.ui.form.field;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.3
 *
 */
public class FormContainerViewField extends CustomField<Object> {
	private static final long serialVersionUID = 1L;
	private CssLayout layout;

	public FormContainerViewField() {
		layout = new CssLayout();
		layout.setWidth("100%");
		layout.setStyleName(UIConstants.FORM_CONTAINER_VIEW);
	}

	public void addComponentField(final Component component) {
		layout.addComponent(component);
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
