package com.mycollab.common;

import java.util.Locale;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class CountryValueFactory {
    private static String[] isoCountries;

    static {
        isoCountries = Locale.getISOCountries();
    }

    public static String[] getCountryList() {
        return isoCountries;
    }
}
