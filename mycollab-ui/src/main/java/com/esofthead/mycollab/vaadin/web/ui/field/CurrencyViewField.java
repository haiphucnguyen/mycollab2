package com.esofthead.mycollab.vaadin.web.ui.field;

import com.esofthead.mycollab.core.utils.CurrencyUtils;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;

import java.util.Currency;

/**
 * @author MyCollab Ltd
 * @since 5.3.1
 */
public class CurrencyViewField extends CustomField<String> {
    private ELabel label;

    public CurrencyViewField(String value) {
        if (StringUtils.isBlank(value)) {
            label = new ELabel();
        } else {
            Currency currency = CurrencyUtils.getInstance(value);
            label = new ELabel(value).withWidth("100%").withStyleName("wordWrap").withWidthUndefined()
                    .withDescription(currency.getDisplayName(AppContext.getUserLocale()));
        }
    }

    @Override
    protected Component initContent() {
        return label;
    }

    @Override
    public Class<? extends String> getType() {
        return String.class;
    }
}
