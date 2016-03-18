package com.esofthead.mycollab.module.project.ui.format;

import com.esofthead.mycollab.module.project.domain.Invoice;
import com.esofthead.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum;
import com.esofthead.mycollab.utils.FieldGroupFormatter;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class InvoiceFieldFormatter extends FieldGroupFormatter {
    private static InvoiceFieldFormatter _instance = new InvoiceFieldFormatter();

    private InvoiceFieldFormatter() {
        super();

        this.generateFieldDisplayHandler(Invoice.Field.contactuserfullname.name(), InvoiceI18nEnum.FORM_CONTACT_PERSON);
    }

    public static InvoiceFieldFormatter instance() {
        return _instance;
    }
}
