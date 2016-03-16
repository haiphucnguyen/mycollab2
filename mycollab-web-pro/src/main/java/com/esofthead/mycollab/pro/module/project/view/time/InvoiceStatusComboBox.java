package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.vaadin.web.ui.I18nValueComboBox;

import java.util.Arrays;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class InvoiceStatusComboBox extends I18nValueComboBox {
    public InvoiceStatusComboBox() {
        super();
        this.setNullSelectionAllowed(false);
        this.setCaption(null);
        this.loadData(Arrays.asList(OptionI18nEnum.invoiceStatuses));
    }
}
