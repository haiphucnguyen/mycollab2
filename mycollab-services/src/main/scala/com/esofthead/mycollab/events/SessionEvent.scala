package com.esofthead.mycollab.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
class SessionEvent {
}

object SessionEvent {

  class UserProfileChangeEvent(source: AnyRef, fieldChange: String, data: AnyRef) extends ApplicationEvent(source, data) {
    def getFieldChange: String = fieldChange
  }

}
