package com.mycollab.ondemand.module.billing.esb.impl

import com.fastspring.FastSpring
import com.fastspring.domain.Subscription
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.module.esb.GenericCommand
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionMapperExt
import com.mycollab.ondemand.module.billing.esb.UpdateBillingPlanEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @version 5.3.5
  */
@Component class UpdateBillingPlanCommandImpl extends GenericCommand {
  @Autowired private val billingSubscriptionMapperExt: BillingSubscriptionMapperExt = null
  @Autowired private val fastSpringService: FastSpring = null
  
  @AllowConcurrentEvents
  @Subscribe
  def updateSubscriptionInformation(event: UpdateBillingPlanEvent): Unit = {
    val billingSubscription = billingSubscriptionMapperExt.findSubscription(event.accountId)
    if (billingSubscription != null) {
      val subscription = new Subscription
      subscription.setReference(billingSubscription.getSubreference)
      subscription.setProductPath(event.billingPlan.getProductpath)
      fastSpringService.updateSubscription(subscription)
    }
  }
}
