package com.mycollab.vaadin.events

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class ViewEvent<out B>(source: Any, val data: B) : ApplicationEvent(source) {
    companion object {
        @JvmField val VIEW_IDENTIFIER = "viewEvent"
    }
}