package com.esofthead.mycollab.vaadin.ui.form.field;

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

    private Label label;
    private String value;

    public DefaultViewField(final String value) {
        this(value, ContentMode.TEXT);
    }

    public DefaultViewField(final String value, final ContentMode contentMode) {
        this.value = value;
        label = new Label();
        label.setWidth("100%");
        label.setContentMode(contentMode);

        if (StringUtils.isNotBlank(value)) {
            label.setValue(value);
            label.setDescription(value);
        } else {
            label.setValue("");
        }
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

    @Override
    protected Component initContent() {
        return label;
    }

    @Override
    protected void setInternalValue(String newValue) {
        label.setValue(newValue);
        label.setDescription(newValue);
    }
}
