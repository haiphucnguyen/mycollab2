package com.mycollab.vaadin.ui.formatter;

import com.mycollab.core.utils.StringUtils;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.ui.ELabel;

import java.util.Locale;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
public class CountryHistoryFieldFormat implements HistoryFieldFormat {
    @Override
    public String toString(String countryCode) {
        if (StringUtils.isNotBlank(countryCode)) {
            Locale obj = new Locale("", countryCode);
            return obj.getDisplayCountry(AppContext.getUserLocale());
        } else {
            return "";
        }
    }

    @Override
    public String toString(String value, Boolean displayAsHtml, String msgIfBlank) {
        return toString(value);
    }
}
