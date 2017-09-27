package com.mycollab.ondemand.module.billing.esb

import com.fastspring.FastSpring
import com.fastspring.domain.Customer
import com.fastspring.domain.Subscription
import com.google.common.eventbus.AllowConcurrentEvents
import com.google.common.eventbus.Subscribe
import com.mycollab.module.esb.GenericCommand
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Component
class UpdateSubscriptionCommand(private val fastSpringService: FastSpring) : GenericCommand() {

    @AllowConcurrentEvents
    @Subscribe
    fun updateSubscriptionInformation(event: UpdateSubscriptionEvent) {
        val billingSubscription = event.subscription
        val subscription = Subscription()
        val customer = Customer()
        customer.phoneNumber = billingSubscription.phone
        customer.lastName = billingSubscription.contactname
        subscription.email = billingSubscription.email
        subscription.phoneNumber = billingSubscription.phone
        subscription.customer = customer
        subscription.reference = billingSubscription.subreference
        fastSpringService.updateSubscription(subscription)
    }
}