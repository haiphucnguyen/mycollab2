package com.esofthead.mycollab.module.user.accountsettings.view.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 5.0.5
 */
object ProfileEvent {
  class GotoProfileView(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoUploadPhoto(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
}
