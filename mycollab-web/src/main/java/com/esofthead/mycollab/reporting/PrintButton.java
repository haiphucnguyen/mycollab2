package com.esofthead.mycollab.reporting;

import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Button;

import java.util.UUID;

/**
 * @author Mycollab Ltd
 * @since 5.2.11
 */
public class PrintButton extends Button {
    private BrowserWindowOpener printWindowOpener;
    private FormReportStreamSource formReportStreamSource;

    public PrintButton() {
        setIcon(FontAwesome.PRINT);
        formReportStreamSource = new FormReportStreamSource(new FormReportTemplateExecutor(""));
        printWindowOpener = new BrowserWindowOpener(new StreamResource(formReportStreamSource, UUID.randomUUID()
                .toString() + ".pdf"));
        printWindowOpener.extend(this);
    }

    public void doPrint(Object bean, FormReportLayout formReportLayout) {
        formReportStreamSource.setBean(bean);
        formReportStreamSource.setFormLayout(formReportLayout);
    }
}
