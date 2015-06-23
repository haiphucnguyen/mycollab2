package com.esofthead.mycollab.premium.module.user.accountsettings.view.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
object SettingExtEvent {

    class GotoGeneralSetting(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

    class GotoCustomizePage(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
    
    class GotoLogoUpload(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
    
}
