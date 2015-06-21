package com.esofthead.mycollab.vaadin.mvp

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
class ViewEvent[E](source: AnyRef, data: E) extends ApplicationEvent(source, data) {
    
}

object ViewEvent {
    val VIEW_IDENTIFIER: String = "viewevent";
}
