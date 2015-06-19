package com.esofthead.mycollab.premium.module.user.accountsettings.view.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
object SettingExtEvent {
    
    class GeneralSettingRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
    
    class GeneralSettingEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
    
    class GotoLogoUpload(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
    
    class GotoTheme(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
    
}
