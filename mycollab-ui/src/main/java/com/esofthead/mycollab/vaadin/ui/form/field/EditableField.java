package com.esofthead.mycollab.vaadin.ui.form.field;

import com.vaadin.event.FieldEvents;
import com.vaadin.event.LayoutEvents;
import com.vaadin.ui.*;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public class EditableField extends CustomField {
    private Field readField, editField;
    private CssLayout wrapper;
    private boolean isRead;

    public EditableField(final Field readField, final Field editField) {
        this.readField = readField;
        this.readField.setWidth("100%");
        this.readField.addStyleName("editable-field");
        ((AbstractComponent) readField).setDescription("Click to edit");
        this.editField = editField;
        wrapper = new CssLayout(readField);
        isRead = true;
        wrapper.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {
                isRead = !isRead;
                if (!isRead) {
                    wrapper.removeComponent(readField);
                    wrapper.addComponent(editField);
                    Object value = readField.getValue();
                    editField.setValue(value);
                    editField.focus();
                }
            }
        });

        editField.setWidth("100%");
        if (editField instanceof FieldEvents.BlurNotifier) {
            ((AbstractComponent) editField).setImmediate(true);
            ((FieldEvents.BlurNotifier) editField).addBlurListener(new FieldEvents.BlurListener() {
                @Override
                public void blur(FieldEvents.BlurEvent event) {
                    wrapper.removeComponent(editField);
                    wrapper.addComponent(readField);
                }
            });
        }
    }

    @Override
    protected Component initContent() {
        return wrapper;
    }

    @Override
    public Class<?> getType() {
        return readField.getType();
    }
}
