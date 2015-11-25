package com.esofthead.mycollab.vaadin.ui.form.field;

import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;

import java.util.Date;

/**
 * @author MyCollab Ltd.
 * @since 4.5.3
 */
public class DateTimeViewField extends CustomField<String> {
    private static final long serialVersionUID = 1L;

    private Date date;

    public DateTimeViewField(Date date) {
        this.date = date;
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

    @Override
    protected Component initContent() {
        final Label l = new Label();
        l.setWidth("100%");
        if (date == null) {
            l.setValue("&nbsp;");
            l.setContentMode(ContentMode.HTML);
        } else {
            l.setValue(AppContext.formatDateTime(date));
        }
        return l;
    }
}
