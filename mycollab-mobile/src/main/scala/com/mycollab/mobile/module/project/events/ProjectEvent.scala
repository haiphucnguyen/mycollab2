package com.mycollab.mobile.module.project.events

import com.mycollab.events.ApplicationEvent

/**
  * @author MyCollab Ltd
  * @since 5.2.5
  */
object ProjectEvent {

  class GotoAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoProjectList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoMyProject(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class MyProjectActivities(source: AnyRef, data: Integer) extends ApplicationEvent(source, data) {}

  class GotoAllActivitiesView(source: AnyRef) extends ApplicationEvent(source, null) {}

  class GotoDashboard(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
