package com.mycollab.ondemand.module.billing.esb

import com.mycollab.ondemand.module.billing.domain.BillingSubscription

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class DeleteSubscriptionEvent(val subscriptions: List<BillingSubscription>) {
}