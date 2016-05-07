package com.esofthead.mycollab.module.crm.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object AccountEvent {

  class GotoAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class Save(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class Search(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
