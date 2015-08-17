package com.esofthead.mycollab.schedule.email.project.impl

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
class ProjectRelayEmailNotificationActionImpl extends SendingRelayEmailNotificationAction{
    override def sendNotificationForCreateAction(notification: SimpleRelayEmailNotification): Unit = {

    }

    override def sendNotificationForUpdateAction(notification: SimpleRelayEmailNotification): Unit = {

    }

    override def sendNotificationForCommentAction(notification: SimpleRelayEmailNotification): Unit = {

    }
}
