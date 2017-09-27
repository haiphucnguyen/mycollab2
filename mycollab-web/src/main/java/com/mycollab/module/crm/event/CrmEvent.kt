package com.mycollab.module.crm.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class CrmEvent {
    class GotoHome(source: Any, data: Any?) : ApplicationEvent(source, data)
}