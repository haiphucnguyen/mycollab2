package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.data.Item;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

public class DefaultEditFormFieldFactory extends DefaultFieldFactory {

    private static final long serialVersionUID = 1L;

    @Override
    public Field createField(Item item, Object propertyId,
            com.vaadin.ui.Component uiContext) {
        Field field = onCreateField(item, propertyId, uiContext);
        if (field == null) {
            field = super.createField(item, propertyId, uiContext);
            if (field instanceof TextField) {
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
                ((TextField) field).setCaption(null);
            }
        } else {
            if (field instanceof AbstractTextField) {
                ((AbstractTextField)field).setNullRepresentation("");
            } else if (field instanceof RichTextArea) {
                ((RichTextArea)field).setNullRepresentation("");
            }
        }
        return field;
    }

    protected Field onCreateField(Item item, Object propertyId,
            com.vaadin.ui.Component uiContext) {
        return null;
    }
}
