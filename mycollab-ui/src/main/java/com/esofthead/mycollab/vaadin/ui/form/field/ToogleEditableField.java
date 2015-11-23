package com.esofthead.mycollab.vaadin.ui.form.field;

import com.esofthead.mycollab.core.MyCollabException;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.LayoutEvents;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;

/**
 * @author MyCollab Ltd
 * @since 5.2.3
 */
public class ToogleEditableField extends EditableField {
    private Field readField;
    private Class<? extends Field> editFieldCls;
    private Field editField;

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
                            if (editField instanceof FieldEvents.BlurNotifier) {
                                ((AbstractComponent) editField).setImmediate(true);
                                ((FieldEvents.BlurNotifier) editField).addBlurListener(new FieldEvents.BlurListener() {
                                    @Override
                                    public void blur(FieldEvents.BlurEvent event) {
                                        isRead = true;
                                        wrapper.removeComponent(editField);
                                        wrapper.addComponent(readField);
                                        wrapper.addStyleName("editable-field");
                                    }
                                });
                            }
                            editField.addValueChangeListener(new ValueChangeListener() {
                                @Override
                                public void valueChange(Property.ValueChangeEvent event) {
                                    System.out.println("Value change");
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
