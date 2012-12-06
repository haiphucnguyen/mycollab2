package com.esofthead.mycollab.vaadin.ui;

import org.apache.commons.beanutils.BeanUtils;
import org.vaadin.addon.customfield.CustomField;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;

public class DefaultFormViewFieldFactory extends DefaultFieldFactory {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public Field createField(Item item, Object propertyId,
			com.vaadin.ui.Component uiContext) {

		Field field = onCreateField(item, propertyId, uiContext);
		if (field == null) {
			Object bean = ((BeanItem<Object>) item).getBean();

			try {
				String propertyValue = BeanUtils.getProperty(bean,
						(String) propertyId);
				field = new FormViewField(propertyValue);
			} catch (Exception e) {
				e.printStackTrace();
				field = new FormViewField("Error");
			}
		}

		return field;
	}

	protected Field onCreateField(Item item, Object propertyId,
			com.vaadin.ui.Component uiContext) {
		return null;
	}

	public static class FormViewField extends CustomField {
		private static final long serialVersionUID = 1L;

		public FormViewField(String value) {
			Label l = new Label();
			l.setWidth("100%");
			this.setCompositionRoot(l);
			l.setValue(value);
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}
	}

	public static class FormLinkViewField extends CustomField {
		private static final long serialVersionUID = 1L;

		public FormLinkViewField(String value, Button.ClickListener listener) {
			ButtonLink l = new ButtonLink(value, listener);
			l.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			this.setCompositionRoot(l);
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}
	}
	
	public static class FormEmailLinkViewField extends CustomField {
		private static final long serialVersionUID = 1L;

		public FormEmailLinkViewField(String email) {
			EmailLink l = new EmailLink(email);
			l.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			this.setCompositionRoot(l);
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}
	}
}
