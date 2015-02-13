package com.esofthead.mycollab.vaadin.ui.form.field;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;
import org.apache.commons.lang3.StringUtils;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public class RoundNumerField extends CustomField<Number> {
    private Number value;
    private ContentMode contentMode;

    private static final long serialVersionUID = 1L;

    public RoundNumerField(final Number value) {
        this.value = value;
    }

    @Override
    public Class<Number> getType() {
        return Number.class;
    }

    @Override
    protected Component initContent() {
        final Label label = new Label();
        label.setWidth("100%");
        label.setContentMode(contentMode);

        if (value != null) {
            double d = value.doubleValue();
            d = Math.round(d*100)/100;
            label.setValue(d + "");
        } else {
            label.setValue("");
        }

        return label;
    }
}
