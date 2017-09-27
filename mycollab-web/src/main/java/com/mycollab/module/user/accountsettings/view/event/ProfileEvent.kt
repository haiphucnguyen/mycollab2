package com.mycollab.module.user.accountsettings.view.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class ProfileEvent {
    class GotoProfileView(source: Any) : ApplicationEvent(source, null)
}