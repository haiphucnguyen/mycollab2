package com.mycollab.module.crm.event

import com.mycollab.events.ApplicationEvent

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object DocumentEvent {

  class GotoDashboard(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
