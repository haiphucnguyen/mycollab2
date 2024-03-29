package com.mycollab.premium.vaadin.ui.service

import com.mycollab.core.BroadcastMessage
import com.mycollab.vaadin.web.ui.service.AbstractBroadcastReceiverService
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class BroadcastReceiverServiceImpl : AbstractBroadcastReceiverService() {
    override fun onBroadcast(message: BroadcastMessage) {
        System.out.println("A: " + message.wrapObj)
    }
}
