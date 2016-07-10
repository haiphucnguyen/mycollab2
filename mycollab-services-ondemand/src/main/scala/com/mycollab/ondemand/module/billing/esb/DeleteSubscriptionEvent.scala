package com.mycollab.ondemand.module.billing.esb

import com.mycollab.ondemand.module.billing.domain.BillingSubscription

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
class DeleteSubscriptionEvent(val subscriptions: java.util.List[BillingSubscription]) {
  
}
