package com.esofthead.mycollab.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object FollowingTicketEvent {

  class GotoMyFollowingItems(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
}
