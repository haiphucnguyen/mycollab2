package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;

import java.io.Serializable;
import java.util.Set;

/**
 * @author MyCollab Ltd
 * @since 5.3.2
 */
public interface IFormLayoutFactory extends Serializable {
    ComponentContainer getLayout();

    Component attachField(Object propertyId, Field<?> field);

    Set<String> bindFields();
}
