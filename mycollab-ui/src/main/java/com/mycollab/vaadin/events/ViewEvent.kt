package com.mycollab.vaadin.events

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class ViewEvent<B>(source: Any, data: Any) : ApplicationEvent(source, data) {
    companion object {
        @JvmField val VIEW_IDENTIFIER = "viewevent"
    }
}