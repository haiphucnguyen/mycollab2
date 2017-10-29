/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http:></http:>//www.gnu.org/licenses/>.
 */
package com.mycollab.module.user.domain

import com.mycollab.core.arguments.NotBindable
import com.mycollab.core.utils.CurrencyUtils
import com.mycollab.i18n.LocalizationHelper
import com.mycollab.module.billing.AccountStatusConstants
import com.google.common.base.MoreObjects

import java.util.Currency
import java.util.Locale

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
open class SimpleBillingAccount : BillingAccount() {

    @NotBindable
    lateinit var billingPlan: BillingPlan

    @NotBindable
    private var currencyInstance: Currency? = null

    @NotBindable
    private var localeInstance: Locale? = null

    val dateFormatInstance: String
        get() = MoreObjects.firstNonNull(defaultyymmddformat, DEFAULT_DATE_FORMAT)

    val shortDateFormatInstance: String
        get() = MoreObjects.firstNonNull(defaultmmddformat, DEFAULT_SHORT_DATE_FORMAT)

    val longDateFormatInstance: String
        get() = MoreObjects.firstNonNull(defaulthumandateformat, DEFAULT_LONG_DATE_FORMAT)

    val dateTimeFormatInstance: String
        get() = MoreObjects.firstNonNull(defaultyymmddformat, DEFAULT_DATE_FORMAT) + " KK:mm a"

    val isNotActive: Boolean?
        get() = AccountStatusConstants.ACTIVE != status

    override fun setDefaultlanguagetag(defaultlanguagetag: String) {
        super.setDefaultlanguagetag(defaultlanguagetag)
    }

    fun getLocaleInstance(): Locale {
        if (localeInstance == null) {
            localeInstance = LocalizationHelper.getLocaleInstance(defaultlanguagetag)
        }
        return localeInstance!!
    }

    fun getCurrencyInstance(): Currency {
        if (currencyInstance == null) {
            currencyInstance = CurrencyUtils.getInstance(defaultcurrencyid)
        }
        return currencyInstance!!
    }

    companion object {
        private val serialVersionUID = 1L

        @JvmField val DEFAULT_DATE_FORMAT = "MM/dd/yyyy"
        @JvmField val DEFAULT_SHORT_DATE_FORMAT = "MM/dd"
        @JvmField val DEFAULT_LONG_DATE_FORMAT = "E, dd MMM yyyy"
    }
}
