package com.esofthead.mycollab.vaadin.web.ui.field;

import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.3.3
 */
public class DateTimeOptionViewField extends CustomField<String> {
    private Date date;

    public DateTimeOptionViewField(Date dateVal) {
        this.date = dateVal;
    }

    @Override
    protected Component initContent() {
        final Label l = new Label();
        l.setWidth("100%");
        if (date == null) {
            l.setValue("&nbsp;");
            l.setContentMode(ContentMode.HTML);
        } else {
            DateTime jodaTime = new DateTime(date);
            if (jodaTime.getMinuteOfHour() > 0 || jodaTime.getHourOfDay() > 0) {
                l.setValue(AppContext.formatDateTime(date));
            } else {
                l.setValue(AppContext.formatDate(date));
            }

        }
        return l;
    }

    @Override
    public Class<? extends String> getType() {
        return String.class;
    }
}
