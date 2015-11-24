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
@SuppressWarnings({"rawtypes", "unchecked"})
public class DateViewField extends CustomField {
    private static final long serialVersionUID = 1L;

    private Date date;
    private Label dateLbl;

    public DateViewField(Date date) {
        this.date = date;
        dateLbl = new Label();
        dateLbl.setWidth("100%");
    }

    @Override
    public Class<?> getType() {
        return Object.class;
    }

    @Override
    protected Component initContent() {
        if (date == null) {
            dateLbl.setValue("&nbsp;");
            dateLbl.setContentMode(ContentMode.HTML);
        } else {
            dateLbl.setValue(AppContext.formatDate(date));
            dateLbl.setDescription(AppContext.formatPrettyTime(date));
        }
        return dateLbl;
    }
}
