package com.mycollab.mobile.module.crm.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class CrmEvent {
    class GotoActivitiesView(source: Any, data: Any?) : ApplicationEvent(source, data)
}