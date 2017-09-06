package com.mycollab.module.user.accountsettings.view.events

import com.mycollab.events.ApplicationEvent

/**
  * @author MyCollab Ltd
  * @since 5.0.5
  */
object ProfileEvent {
  
  class GotoProfileView(source: AnyRef) extends ApplicationEvent(source, null) {}
  
}
