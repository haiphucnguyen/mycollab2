package com.mycollab.module.project.schedule.email.service

import com.mycollab.schedule.email.SendingRelayEmailNotificationAction
import com.mycollab.common.domain.SimpleRelayEmailNotification

/**
  * @author MyCollab Ltd
  * @since 5.1.2
  */
class StandupRelayEmailNotificationActionImpl extends SendingRelayEmailNotificationAction {
  override def sendNotificationForCreateAction(notification: SimpleRelayEmailNotification): Unit = {

  }

  override def sendNotificationForUpdateAction(notification: SimpleRelayEmailNotification): Unit = {

  }

  override def sendNotificationForCommentAction(notification: SimpleRelayEmailNotification): Unit = {

  }
}
