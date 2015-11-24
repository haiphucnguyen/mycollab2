package com.esofthead.mycollab.vaadin.ui.form.field;

import com.vaadin.data.Property;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;
import org.apache.commons.lang3.StringUtils;

/**
 * @author MyCollab Ltd.
 * @since 4.5.3
 */
public class DefaultViewField extends CustomField<String> {
    private static final long serialVersionUID = 1L;

    protected Label label;
    protected String value;

    public DefaultViewField(ContentMode contentMode) {
        label = new Label();
        label.setWidth("100%");
        label.setContentMode(contentMode);
    }

    public DefaultViewField(final String value) {
        this(value, ContentMode.TEXT);
    }

    public DefaultViewField(final String value, final ContentMode contentMode) {
        this.value = value;
        label = new Label(value);
        label.setWidth("100%");
        label.setContentMode(contentMode);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

    protected String getValueAsHtml() {
        return value;
    }

    @Override
    protected Component initContent() {
        return label;
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        Object valueProp = newDataSource.getValue();
        value = (valueProp != null) ? valueProp.toString() : "";
        if (StringUtils.isNotBlank(value)) {
            if (label.getContentMode() == ContentMode.HTML) {
                label.setValue(getValueAsHtml());
            } else {
                label.setValue(value);
            }

            label.setDescription(value);
        } else {
            label.setValue("");
        }
        super.setPropertyDataSource(newDataSource);
    }
}
