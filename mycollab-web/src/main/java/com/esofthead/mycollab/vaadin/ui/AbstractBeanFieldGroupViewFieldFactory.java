package com.esofthead.mycollab.vaadin.ui;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.utils.ClassUtils;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormViewField;
import com.vaadin.ui.Field;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
public abstract class AbstractBeanFieldGroupViewFieldFactory<B> implements
		IBeanFieldGroupFieldFactory<B> {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(AbstractBeanFieldGroupViewFieldFactory.class);

	protected GenericBeanForm<B> attachForm;

	public AbstractBeanFieldGroupViewFieldFactory(GenericBeanForm<B> form) {
		this.attachForm = form;
	}

	@Override
	public void setBean(B bean) {
		Class<?> beanClass = bean.getClass();
		java.lang.reflect.Field[] fields = ClassUtils.getAllFields(beanClass);
		for (java.lang.reflect.Field field : fields) {
			if ("selected".equals(field.getName())
					|| "extraData".equals(field.getName())) {
				continue;
			}
			bindField(field.getName());
		}
	}

	@Override
	public final Field<?> bindField(Object propertyId) {
		Field<?> field = onCreateField(propertyId);
		if (field == null) {

			try {
				final String propertyValue = BeanUtils.getProperty(
						attachForm.getBean(), (String) propertyId);
				field = new FormViewField(propertyValue);
			} catch (final Exception e) {
				log.error("Error while get field value", e);
				field = new FormViewField("Error");
			}
		}

		attachForm.attachField(propertyId, field);

		return field;
	}

	@Override
	public void commit() {

	}

	abstract protected Field<?> onCreateField(Object propertyId);

}
