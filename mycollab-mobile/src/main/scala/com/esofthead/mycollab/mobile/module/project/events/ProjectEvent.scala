package com.esofthead.mycollab.mobile.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
  * @author MyCollab Ltd
  * @since 5.2.5
  */
object ProjectEvent {

  class GotoAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoProjectList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoMyProject(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class MyProjectActivities(source: AnyRef, data: Integer) extends ApplicationEvent(source, data) {}

  class AllActivities(source: AnyRef) extends ApplicationEvent(source, null) {}

}
