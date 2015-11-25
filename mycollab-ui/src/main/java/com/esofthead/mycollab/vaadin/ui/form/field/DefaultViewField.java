package com.esofthead.mycollab.vaadin.ui.form.field;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 * @since 4.5.3
 */
public class DefaultViewField extends CustomField<String> {
    private static final long serialVersionUID = 1L;

    protected Label label;
    protected String value;

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
}
