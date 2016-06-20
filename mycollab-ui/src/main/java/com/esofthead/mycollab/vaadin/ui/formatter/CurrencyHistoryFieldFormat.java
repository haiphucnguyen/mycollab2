package com.esofthead.mycollab.vaadin.ui.formatter;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.utils.CurrencyUtils;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.vaadin.AppContext;
import com.hp.gagawa.java.elements.Span;

import java.util.Currency;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public class CurrencyHistoryFieldFormat implements HistoryFieldFormat {

    @Override
    public String toString(String value) {
        return toString(value, true, AppContext.getMessage(GenericI18Enum.FORM_EMPTY));
    }

    @Override
    public String toString(String value, Boolean displayAsHtml, String msgIfBlank) {
        if (StringUtils.isNotBlank(value)) {
            Currency currency = CurrencyUtils.getInstance(value);
            if (displayAsHtml) {
                return new Span().appendText(value).setTitle(currency.getDisplayName(AppContext.getUserLocale())).write();
            } else {
                return value;
            }
        } else {
            return msgIfBlank;
        }
    }
}
