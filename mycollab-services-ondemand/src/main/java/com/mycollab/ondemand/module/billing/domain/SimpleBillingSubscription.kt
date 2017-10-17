package com.mycollab.ondemand.module.billing.domain

import com.mycollab.core.arguments.NotBindable
import com.mycollab.module.user.domain.BillingPlan
import org.joda.time.DateTime

import java.util.Date

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
class SimpleBillingSubscription : BillingSubscription() {
    var expireDate: Date? = null

    @NotBindable
    var billingPlan: BillingPlan? = null

    val isValid: Boolean?
        get() = if (expireDate != null) DateTime(expireDate).isAfter(DateTime()) else false

    fun canAccess(): Boolean? = if (expireDate != null) DateTime(expireDate).isAfter(DateTime().minus(7)) else false
}
