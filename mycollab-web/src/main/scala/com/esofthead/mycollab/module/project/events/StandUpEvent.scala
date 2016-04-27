package com.esofthead.mycollab.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object StandUpEvent {

  class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class DisplayStandupInProject(source: AnyRef, data: Integer) extends ApplicationEvent(source, data) {}

}
