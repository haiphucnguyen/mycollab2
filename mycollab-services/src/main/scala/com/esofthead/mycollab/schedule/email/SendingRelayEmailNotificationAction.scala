package com.esofthead.mycollab.schedule.email

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
trait SendingRelayEmailNotificationAction {
    def sendNotificationForCreateAction(notification: SimpleRelayEmailNotification): Unit

    def sendNotificationForUpdateAction(notification: SimpleRelayEmailNotification): Unit

    def sendNotificationForCommentAction(notification: SimpleRelayEmailNotification): Unit
}
