package com.esofthead.mycollab.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object ProjectEvent {

  class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoMyProject(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoTagListView(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoSearchItemView(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
}
