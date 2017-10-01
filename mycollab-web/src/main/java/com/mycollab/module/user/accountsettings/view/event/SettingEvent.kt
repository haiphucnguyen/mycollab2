package com.mycollab.module.user.accountsettings.view.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object SettingEvent {
    class GotoGeneralSetting(source: Any, val data: Any?) : ApplicationEvent(source)

    class GotoTheme(source: Any, val data: Any?) : ApplicationEvent(source)

    class SaveTheme(source: Any, val data: Any?) : ApplicationEvent(source)

    class ResetTheme(source: Any, val data: Any?) : ApplicationEvent(source)
}