package com.mycollab.mobile.module.crm.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object CrmEvent {
    class GotoActivitiesView(source: Any, val data: Any?) : ApplicationEvent(source)
}