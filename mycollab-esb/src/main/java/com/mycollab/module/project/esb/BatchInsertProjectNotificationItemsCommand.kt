package com.mycollab.module.project.esb

import com.google.common.eventbus.AllowConcurrentEvents
import com.google.common.eventbus.Subscribe
import com.mycollab.common.service.NotificationItemService
import com.mycollab.core.BroadcastMessage
import com.mycollab.core.Broadcaster
import com.mycollab.module.esb.GenericCommand
import com.mycollab.module.project.ProjectEntryUpdateNotification
import com.mycollab.module.project.event.BatchInsertNotificationItemsEvent
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Component
class BatchInsertProjectNotificationItemsCommand(private val notificationItemsService: NotificationItemService) : GenericCommand() {

    @AllowConcurrentEvents
    @Subscribe
    fun insertItems(event: BatchInsertNotificationItemsEvent) {
        if (event.notifyUsers.isNotEmpty()) {
            notificationItemsService.batchInsertItems(event.notifyUsers, event.module, event.type, event.typeId, event.messages, event.sAccountId)
            event.notifyUsers.forEachIndexed { index, notifyUser -> Broadcaster.broadcast(BroadcastMessage(event.sAccountId, notifyUser,
                    ProjectEntryUpdateNotification(event.type, event.typeId, event.messages[index]))) }
        }
    }
}