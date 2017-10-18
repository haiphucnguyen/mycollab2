package com.mycollab.ondemand.vaadin.ui.service.impl

import com.mycollab.core.BroadcastMessage
import com.mycollab.vaadin.web.ui.service.AbstractBroadcastReceiverService
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@Service
class BroadcastReceiverServiceImpl : AbstractBroadcastReceiverService() {
    override fun onBroadcast(message: BroadcastMessage) {
        if (message.sAccountId != null) {
            val context = myCollabApp.associateContext
            if (context.isMatchAccount(message.sAccountId)) {
                myCollabApp.reloadPage()
            }
        }
    }
}
