package com.mycollab.pro.module.project.view.time;

import com.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.mycollab.vaadin.web.ui.I18nValueComboBox;

import java.util.Arrays;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class PricingTypeField extends I18nValueComboBox {
    public PricingTypeField() {
        super();
        this.setNullSelectionAllowed(false);
        this.setCaption(null);
        this.loadData(Arrays.asList(InvoiceI18nEnum.FIX_PRICE, InvoiceI18nEnum.TIME_MATERIAL));
    }
}
