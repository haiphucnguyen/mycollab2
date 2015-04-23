package com.esofthead.mycollab.module.user.accountsettings.view.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
 * @author MyCollab Ltd.
 * @since 5.0.5
 */
object AccountCustomizeEvent {
  class GotoCustomize(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoUploadLogo(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class SaveTheme(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class ResetTheme(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
}
