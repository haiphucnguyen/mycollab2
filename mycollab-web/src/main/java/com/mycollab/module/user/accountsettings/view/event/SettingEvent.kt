package com.mycollab.module.user.accountsettings.view.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class SettingEvent {
    class GotoGeneralSetting(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoTheme(source: Any, data: Any?) : ApplicationEvent(source, data)

    class SaveTheme(source: Any, data: Any?) : ApplicationEvent(source, data)

    class ResetTheme(source: Any, data: Any?) : ApplicationEvent(source, data)
}