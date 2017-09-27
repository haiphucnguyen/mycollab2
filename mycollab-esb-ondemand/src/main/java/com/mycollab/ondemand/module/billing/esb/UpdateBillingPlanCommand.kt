package com.mycollab.ondemand.module.billing.esb

import com.fastspring.FastSpring
import com.fastspring.domain.Subscription
import com.google.common.eventbus.AllowConcurrentEvents
import com.google.common.eventbus.Subscribe
import com.mycollab.module.esb.GenericCommand
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionMapperExt
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Component
class UpdateBillingPlanCommand(private val billingSubscriptionMapperExt: BillingSubscriptionMapperExt,
                               private val fastSpringService: FastSpring) : GenericCommand() {

    @AllowConcurrentEvents
    @Subscribe
    fun updateSubscriptionInformation(event: UpdateBillingPlanEvent) {
        val billingSubscription = billingSubscriptionMapperExt.findSubscription(event.accountId)
        if (billingSubscription != null) {
            val subscription = Subscription()
            subscription.reference = billingSubscription.subreference
            subscription.productPath = event.billingPlan.productpath
            fastSpringService.updateSubscription(subscription)
        }
    }
}