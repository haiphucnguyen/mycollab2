package com.mycollab.module.user.accountsettings.view.events

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd.
 * @since 5.0.5
 */
object SettingEvent {
    
    class GotoGeneralSetting(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoTheme(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
    
    class SaveTheme(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
    
    class ResetTheme(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
    
}
