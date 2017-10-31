package com.mycollab.ondemand.module.billing.dao

import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionHistory
import com.mycollab.ondemand.module.billing.domain.SimpleBillingSubscription
import org.apache.ibatis.annotations.Param

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
interface BillingSubscriptionMapperExt {
    fun findSubscription(sAccountId: Int): SimpleBillingSubscription?

    fun getTheLastBillingSuccess(@Param("sAccountId") sAccountId: Int): BillingSubscriptionHistory?
}
