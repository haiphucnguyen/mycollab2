package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NotBindable;
import com.esofthead.mycollab.core.utils.ClassUtils;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;

/**
 * 
 * @author MyCollab Ltd
 * @since 3.0
 * 
 * @param <B>
 */
public abstract class AbstractBeanFieldGroupEditFieldFactory<B> implements
		IBeanFieldGroupFieldFactory<B> {
	private static final long serialVersionUID = 1L;
	protected GenericBeanForm<B> attachForm;
	protected FieldGroup fieldGroup;

	public AbstractBeanFieldGroupEditFieldFactory(GenericBeanForm<B> form) {
		this.attachForm = form;
		this.fieldGroup = new FieldGroup();
		this.fieldGroup.setBuffered(true);
	}

	@Override
	public void setBean(B bean) {
		fieldGroup.setItemDataSource(new BeanItem<B>(bean));
		Class<?> beanClass = bean.getClass();
		java.lang.reflect.Field[] fields = ClassUtils.getAllFields(beanClass);
		for (java.lang.reflect.Field field : fields) {
			Field<?> formField = onCreateField(field.getName());
			if (formField == null) {
				if (field.getAnnotation(NotBindable.class) != null) {
					continue;
				} else {
					formField = fieldGroup.buildAndBind(field.getName());
				}
			} else {
				fieldGroup.bind(formField, field.getName());
			}

			if (formField instanceof AbstractTextField) {
				((AbstractTextField) formField).setNullRepresentation("");
			} else if (formField instanceof RichTextArea) {
				((RichTextArea) formField).setNullRepresentation("");
			}

			attachForm.attachField(field.getName(), formField);
		}
	}

	@Override
	public void commit() {
		try {
			fieldGroup.commit();
		} catch (CommitException e) {
			throw new MyCollabException(e);
		}
	}

	abstract protected Field<?> onCreateField(Object propertyId);
}
