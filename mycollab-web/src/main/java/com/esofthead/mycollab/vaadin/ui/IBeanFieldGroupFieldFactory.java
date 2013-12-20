package com.esofthead.mycollab.vaadin.ui;

import java.io.Serializable;

import com.vaadin.ui.Field;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 * @param <B>
 */
public interface IBeanFieldGroupFieldFactory<B> extends Serializable {

	void setBean(B bean);

	Field<?> bindField(Object propertyId);

	void commit();
}
