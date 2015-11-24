package com.esofthead.mycollab.vaadin.ui.form.field;

import com.esofthead.mycollab.core.utils.StringUtils;
import com.vaadin.data.Property;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 * @since 4.5.3
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class RichTextViewField extends CustomField {
    private static final long serialVersionUID = 1L;

    private String value;
    private Label label;


    public RichTextViewField() {
        this("");
    }

    public RichTextViewField(String value) {
        label = new Label(value, ContentMode.HTML);
        label.setWidth("100%");
        label.addStyleName("wordWrap");
    }

    @Override
    public Class<?> getType() {
        return String.class;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    protected Component initContent() {
        return label;
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        Object propValue = newDataSource.getValue();
        value = (propValue != null) ? propValue.toString() : "";
        label.setValue(StringUtils.formatRichText(value));
        super.setPropertyDataSource(newDataSource);
    }
}
