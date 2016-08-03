package com.mycollab.ondemand.module.billing.esb

import com.fastspring.FastSpring
import com.fastspring.domain.{Customer, Subscription}
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.module.esb.GenericCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
@Component class UpdateSubscriptionCommandImpl extends GenericCommand {
  @Autowired private val fastSpringService: FastSpring = null

  @AllowConcurrentEvents
  @Subscribe
  def updateSubscriptionInformation(event: UpdateSubscriptionEvent): Unit = {
    val billingSubscription = event.subscription
    val subscription = new Subscription
    val customer = new Customer
    customer.setPhoneNumber(billingSubscription.getPhone)
    customer.setLastName(billingSubscription.getContactname)
    subscription.setEmail(billingSubscription.getEmail)
    subscription.setPhoneNumber(billingSubscription.getPhone)
    subscription.setCustomer(customer)
    subscription.setReference(billingSubscription.getSubreference)
    fastSpringService.updateSubscription(subscription)
  }
}
