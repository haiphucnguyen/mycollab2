package com.mycollab.module.user.accountsettings.view.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object AccountBillingEvent {
    class CancelAccount(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoSummary(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoHistory(source: Any, data: Any?) : ApplicationEvent(source, data)
}