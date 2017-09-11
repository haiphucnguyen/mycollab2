package com.mycollab.common

import java.util.Locale

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
object CountryValueFactory {
    var countryList: Array<String>? = null
        private set

    init {
        countryList = Locale.getISOCountries()
    }
}
