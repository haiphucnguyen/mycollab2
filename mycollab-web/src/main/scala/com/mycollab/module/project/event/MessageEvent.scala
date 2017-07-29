package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object MessageEvent {

  class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
}
