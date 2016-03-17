package com.esofthead.mycollab.module.project.ui.format;

import com.esofthead.mycollab.utils.FieldGroupFormatter;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class InvoiceFieldFormatter extends FieldGroupFormatter {
    private static InvoiceFieldFormatter _instance = new InvoiceFieldFormatter();

    private InvoiceFieldFormatter() {

    }

    public static InvoiceFieldFormatter instance() {
        return _instance;
    }
}
