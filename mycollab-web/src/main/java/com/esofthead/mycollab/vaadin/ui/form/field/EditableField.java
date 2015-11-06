package com.esofthead.mycollab.vaadin.ui.form.field;

import com.vaadin.event.LayoutEvents;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public abstract class EditableField<T> extends CustomField<T> {
    private CustomField<T> readField, editField;
    private CssLayout wrapper;
    private boolean isRead;

    public EditableField(final CustomField<T> readField, final CustomField<T> editField) {
        this.readField = readField;
        this.editField = editField;
        wrapper = new CssLayout(readField);
        isRead = true;
        wrapper.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {
                isRead = !isRead;
                if (isRead) {
                    wrapper.removeComponent(editField);
                    wrapper.addComponent(readField);
                } else {
                    wrapper.removeComponent(readField);
                    wrapper.addComponent(editField);
                }
            }
        });
    }

    @Override
    protected Component initContent() {
        return wrapper;
    }

    @Override
    public Class<? extends T> getType() {
        return readField.getType();
    }
}
