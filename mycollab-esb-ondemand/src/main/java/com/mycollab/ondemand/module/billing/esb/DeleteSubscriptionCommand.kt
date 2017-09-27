package com.mycollab.ondemand.module.billing.esb

import com.fastspring.FastSpring
import com.google.common.eventbus.AllowConcurrentEvents
import com.google.common.eventbus.Subscribe
import com.mycollab.module.esb.GenericCommand
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Component
class DeleteSubscriptionCommand(private val fastSpringService: FastSpring) : GenericCommand() {

    @AllowConcurrentEvents
    @Subscribe
    fun deleteAccount(event: DeleteSubscriptionEvent) {
        val subscriptions = event.subscriptions
        subscriptions.forEach { fastSpringService.cancelSubscription(it.subreference) }
    }
}