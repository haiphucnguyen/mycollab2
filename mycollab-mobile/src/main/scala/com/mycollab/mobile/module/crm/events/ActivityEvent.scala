package com.mycollab.mobile.module.crm.events

import com.mycollab.events.ApplicationEvent

/**
  * @author MyCollab Ltd
  * @since 5.4.9
  */
object ActivityEvent {

  class TaskAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class TaskEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class TaskRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class MeetingAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class MeetingEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class MeetingRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class CallAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class CallEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class CallRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoRelatedItems(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
