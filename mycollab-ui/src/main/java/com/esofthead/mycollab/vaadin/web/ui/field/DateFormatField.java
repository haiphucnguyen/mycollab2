package com.esofthead.mycollab.vaadin.web.ui.field;

import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.data.Validator;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author MyCollab Ltd
 * @since 5.3.1
 */
public class DateFormatField extends CustomField<String> {
    private String dateFormat;
    private TextField dateInput;
    private Label dateExample;
    private Date now;
    private SimpleDateFormat dateFormatInstance;

    public DateFormatField(final String initialFormat) {
        this.dateFormat = initialFormat;
        dateInput = new TextField(null, initialFormat);
        dateInput.setImmediate(true);
        dateInput.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.LAZY);
        now = new GregorianCalendar().getTime();
        dateExample = new Label();
        dateFormatInstance = DateTimeUtils.getDateFormat(dateFormat);
        dateExample.setValue("(" + dateFormatInstance.format(now) + ")");
        dateExample.setWidthUndefined();
        dateInput.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                try {
                    dateFormatInstance.applyPattern(event.getText());
                    dateExample.setValue("(" + dateFormatInstance.format(now) + ")");
                } catch (Exception e) {
                    NotificationUtil.showErrorNotification("Invalid format");
                    dateInput.setValue(initialFormat);
                    dateFormatInstance.applyPattern(initialFormat);
                    dateExample.setValue("(" + dateFormatInstance.format(now) + ")");
                }
            }
        });
    }

    @Override
    public void commit() throws SourceException, Validator.InvalidValueException {
        setInternalValue(dateInput.getValue());
        super.commit();
    }

    @Override
    protected Component initContent() {
        return new MHorizontalLayout(dateInput, dateExample);
    }

    @Override
    public Class<? extends String> getType() {
        return String.class;
    }
}
