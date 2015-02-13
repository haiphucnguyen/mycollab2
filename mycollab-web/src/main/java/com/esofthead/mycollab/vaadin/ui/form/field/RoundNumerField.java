package com.esofthead.mycollab.vaadin.ui.form.field;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public class RoundNumerField extends CustomField<Number> {
    private Number value;

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

        if (value != null) {
            double d = value.doubleValue();
            d = Math.round(d*100);
            d = d/100;
            label.setValue(d + "");
        } else {
            label.setValue("");
        }

        return label;
    }
}
