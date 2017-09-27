package com.mycollab.module.crm.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object CrmSettingEvent {
    class GotoNotificationSetting(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoCustomViewSetting(source: Any, data: Any?) : ApplicationEvent(source, data)
}