package com.esofthead.mycollab.module.user.accountsettings.view.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 5.0.5
 */
object SetupEvent {
  class GotoSetupPage(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
