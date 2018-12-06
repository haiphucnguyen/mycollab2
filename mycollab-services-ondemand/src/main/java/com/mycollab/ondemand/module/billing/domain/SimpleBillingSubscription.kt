package com.mycollab.ondemand.module.billing.domain

import com.mycollab.core.arguments.NotBindable
import com.mycollab.module.user.domain.BillingPlan
import java.time.LocalDate

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
class SimpleBillingSubscription : BillingSubscription() {
    var expireDate: LocalDate? = null

    @NotBindable
    var billingPlan: BillingPlan? = null

    val isValid: Boolean?
        get() = if (expireDate != null) expireDate!!.isAfter(LocalDate.now()) else false

    fun canAccess(): Boolean? = if (expireDate != null) expireDate!!.isAfter(LocalDate.now().minusDays(7)) else false
}
