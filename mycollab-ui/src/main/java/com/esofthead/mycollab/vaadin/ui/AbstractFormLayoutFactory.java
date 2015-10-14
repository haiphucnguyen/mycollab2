package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public abstract class AbstractFormLayoutFactory implements IFormLayoutFactory {
    private List<Object> bindProperties = new ArrayList<>();

    @Override
    public boolean hasFieldAttached(Object propertyId) {
        return bindProperties.contains(propertyId);
    }

    @Override
    public final void attachField(Object propertyId, Field<?> field) {
        bindProperties.add(propertyId);
        onAttachField(propertyId, field);
    }

    abstract protected void onAttachField(Object propertyId, Field<?> field);
}
