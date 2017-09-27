package com.mycollab.module.user.accountsettings.view.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class SetupEvent {
    class GotoSetupPage(source: Any, data: Any?) : ApplicationEvent(source, data)
}