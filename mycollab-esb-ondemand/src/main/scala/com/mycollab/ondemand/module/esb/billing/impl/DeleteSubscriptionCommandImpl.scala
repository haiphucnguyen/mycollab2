package com.mycollab.ondemand.module.esb.billing.impl

import com.google.common.eventbus.Subscribe
import com.mycollab.billing.fastspring.FastSpring
import com.mycollab.module.esb.GenericCommand
import com.mycollab.ondemand.module.billing.esb.DeleteSubscriptionEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
@Component class DeleteSubscriptionCommandImpl extends GenericCommand {
  @Autowired private val fastSpringService: FastSpring = null

  @Subscribe
  def deleteAccount(event: DeleteSubscriptionEvent): Unit = {
    import collection.JavaConverters._
    val subscriptions = event.subscriptions.asScala
    for (subscription <- subscriptions) {
      fastSpringService.cancelSubscription(subscription.getSubreference)
    }
  }
}
