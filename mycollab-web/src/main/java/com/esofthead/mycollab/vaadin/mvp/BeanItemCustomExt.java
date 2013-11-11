package com.esofthead.mycollab.vaadin.mvp;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.form.domain.FormCustomFieldValueWithBLOBs;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.MethodProperty;

public class BeanItemCustomExt<T> extends BeanItem<T> {
	private static final long serialVersionUID = 1L;

	private static List<String> excludeFields = Arrays.asList("id", "module",
			"typeid", "SerialVersionUID", "serialVersionUID");
	private static Set<String> customFieldNames;

	static {
		customFieldNames = new HashSet<String>();
		Class<FormCustomFieldValueWithBLOBs> customFormCls = FormCustomFieldValueWithBLOBs.class;
		for (Class cls = customFormCls; cls != null; cls = cls.getSuperclass()) {
			Field[] declaredFields = cls.getDeclaredFields();
			for (Field field : declaredFields) {
				if (!excludeFields.contains(field.getName())) {
					customFieldNames.add(field.getName());
				}
			}
		}
	}

	public static void init() {
	}

	public BeanItemCustomExt(T bean) {
		super(bean);

		try {
			for (String customFieldName : customFieldNames) {
				this.addItemProperty(customFieldName, new MethodProperty<T>(
						PropertyUtils.getProperty(bean, "customfield"),
						customFieldName));
			}

		} catch (Exception e) {
			throw new MyCollabException(e);
		}

	}

}
