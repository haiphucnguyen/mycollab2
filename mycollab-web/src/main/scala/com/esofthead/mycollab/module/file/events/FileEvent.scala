package com.esofthead.mycollab.module.file.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
 * @author MyCollab 5.1.1
 */
object FileEvent {

    class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
