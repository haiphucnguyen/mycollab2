package com.mycollab.module.user.accountsettings.view.events

import com.mycollab.events.ApplicationEvent

/**
  * @author MyCollab Ltd
  * @since 5.0.5
  */
object AccountBillingEvent {

  class CancelAccount(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoSummary(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoHistory(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
