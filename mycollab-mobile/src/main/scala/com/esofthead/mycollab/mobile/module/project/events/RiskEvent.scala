package com.esofthead.mycollab.mobile.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
  * @author MyCollab Ltd
  * @since 5.2.7
  */
object RiskEvent {

  class GotoAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
