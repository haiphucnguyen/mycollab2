package com.esofthead.mycollab.module.user.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
object RoleEvent {

    class Search(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
