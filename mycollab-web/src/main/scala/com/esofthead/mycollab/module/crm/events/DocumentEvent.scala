package com.esofthead.mycollab.module.crm.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object DocumentEvent {

  class GotoDashboard(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
}
