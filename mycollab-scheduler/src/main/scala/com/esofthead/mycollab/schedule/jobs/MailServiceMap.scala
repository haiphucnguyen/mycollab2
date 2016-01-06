package com.esofthead.mycollab.schedule.jobs

import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction

/**
  * @author MyCollab Ltd
  * @since 5.2.5
  */
object MailServiceMap {
    private val mailServices: Map[String, Class[SendingRelayEmailNotificationAction]] = Map()

    def service(typeVal: String): Class[SendingRelayEmailNotificationAction] = {
        return null
    }
}
