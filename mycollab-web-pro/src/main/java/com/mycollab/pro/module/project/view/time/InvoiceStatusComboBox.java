package com.mycollab.pro.module.project.view.time;

import com.mycollab.module.project.i18n.OptionI18nEnum;
import com.mycollab.module.project.i18n.OptionI18nEnum.InvoiceStatus;
import com.mycollab.vaadin.web.ui.I18nValueComboBox;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class InvoiceStatusComboBox extends I18nValueComboBox<InvoiceStatus> {
    public InvoiceStatusComboBox() {
        super(InvoiceStatus.class, OptionI18nEnum.invoiceStatuses);
    }
}
