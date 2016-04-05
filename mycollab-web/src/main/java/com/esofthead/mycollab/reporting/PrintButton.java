package com.esofthead.mycollab.reporting;

import com.esofthead.mycollab.utils.FieldGroupFormatter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

/**
 * @author Mycollab Ltd
 * @since 5.2.11
 */
public class PrintButton extends Button {
    private PrintWindowOpener printWindowOpener;
    private FormReportStreamSource formReportStreamSource;

    public PrintButton() {
        setIcon(FontAwesome.PRINT);
        formReportStreamSource = new FormReportStreamSource(new FormReportTemplateExecutor(""));
        printWindowOpener = new PrintWindowOpener(this, formReportStreamSource);
        printWindowOpener.extend(this);
    }

    public void doPrint(Object bean, FormReportLayout formReportLayout, FieldGroupFormatter fieldGroupFormatter) {
        formReportStreamSource.setBean(bean);
        formReportStreamSource.setFormLayout(formReportLayout);
        formReportStreamSource.setFieldGroupFormatter(fieldGroupFormatter);
    }
}
