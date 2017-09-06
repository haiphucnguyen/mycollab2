package com.mycollab.core.utils;

import java.util.Currency;
import java.util.Locale;

/**
 * @author MyCollab Ltd
 * @since 5.3.1
 */
public class CurrencyUtils {
    public static Currency getInstance(String currencyCode) {
        try {
            return Currency.getInstance(currencyCode);
        } catch (Exception e) {
            return Currency.getInstance(Locale.US);
        }
    }
}
