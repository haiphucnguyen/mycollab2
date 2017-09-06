package com.mycollab.module.crm.event

import com.mycollab.events.ApplicationEvent

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object CrmEvent {

  class GotoHome(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
