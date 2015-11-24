package com.esofthead.mycollab.vaadin.ui.form.field;

import com.esofthead.mycollab.core.MyCollabException;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.event.LayoutEvents;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;

/**
 * @author MyCollab Ltd
 * @since 5.2.3
 */
public class ToogleEditableField extends EditableField {
    private Field readField, editField;
    private Class<? extends Field> editFieldCls;

    public ToogleEditableField(final Field readField, final Class<? extends Field> editFieldCls) {
        super();
        this.readField = readField;
        this.editFieldCls = editFieldCls;

        isRead = true;
        wrapper.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {
                isRead = !isRead;
                if (!isRead) {
                    if (editField == null) {
                        try {
                            editField = editFieldCls.newInstance();
                            editField.setWidth("100%");
                            editField.addValueChangeListener(new ValueChangeListener() {
                                @Override
                                public void valueChange(Property.ValueChangeEvent event) {
                                    isRead = true;
                                    wrapper.removeComponent(editField);
                                    wrapper.addComponent(readField);
                                    Object property = editField.getValue();
                                    readField.setPropertyDataSource(new ObjectProperty(property));
                                    wrapper.addStyleName("editable-field");
                                }
                            });
                        } catch (InstantiationException | IllegalAccessException e) {
                            throw new MyCollabException(e);
                        }
                    }

                    wrapper.removeComponent(readField);
                    wrapper.addComponent(editField);
                    Object value = readField.getValue();
                    editField.setValue(value);
                    editField.focus();
                    wrapper.removeStyleName("editable-field");
                }
            }
        });
        wrapper.addComponent(readField);
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        readField.setPropertyDataSource(newDataSource);
    }

    @Override
    public Property getPropertyDataSource() {
        return readField.getPropertyDataSource();
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
