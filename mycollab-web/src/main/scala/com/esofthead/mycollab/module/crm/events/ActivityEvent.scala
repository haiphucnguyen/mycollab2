package com.esofthead.mycollab.module.crm.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object ActivityEvent {

  class GotoCalendar(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoTodoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class TaskAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class TaskEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class TaskRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class MeetingAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class MeetingEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class MeetingRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class CallAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class CallEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class CallRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
