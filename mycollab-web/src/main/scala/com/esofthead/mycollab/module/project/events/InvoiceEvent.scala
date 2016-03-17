package com.esofthead.mycollab.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
  * @author MyCollab Ltd
  * @since 5.2.10
  */
object InvoiceEvent {

  class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class NewInvoiceAdded(source: AnyRef, data: Integer) extends ApplicationEvent(source, data) {}
}
