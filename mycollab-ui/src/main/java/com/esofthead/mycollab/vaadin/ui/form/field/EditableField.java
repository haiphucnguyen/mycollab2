package com.esofthead.mycollab.vaadin.ui.form.field;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public abstract class EditableField extends CustomField {
    protected CssLayout wrapper;
    protected boolean isRead;

    public EditableField() {
        wrapper = new CssLayout();
        wrapper.setWidth("100%");
        wrapper.addStyleName("editable-field");
        wrapper.setDescription("Click to edit");
    }
}
