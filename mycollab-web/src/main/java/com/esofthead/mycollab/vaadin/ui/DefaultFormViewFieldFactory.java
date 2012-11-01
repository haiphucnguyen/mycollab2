package com.esofthead.mycollab.vaadin.ui;

import org.apache.commons.beanutils.BeanUtils;
import org.vaadin.addon.customfield.CustomField;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;

public class DefaultFormViewFieldFactory extends DefaultFieldFactory {
	private static final long serialVersionUID = 1L;

	@Override
	public Field createField(Item item, Object propertyId,
			com.vaadin.ui.Component uiContext) {
		Object bean = ((BeanItem<Object>) item).getBean();
		FormViewField field;

		try {
			String propertyValue = BeanUtils.getProperty(bean,
					(String) propertyId);
			field = new FormViewField(propertyValue);
		} catch (Exception e) {
			e.printStackTrace();
			field = new FormViewField("Error");
		}

		return field;
	}

	private class FormViewField extends CustomField {
		private static final long serialVersionUID = 1L;

		public FormViewField(String value) {
			Label l = new Label();
			l.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			this.setCompositionRoot(l);
			l.setValue(value);
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}
	}
}
