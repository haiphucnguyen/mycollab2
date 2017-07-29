package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.SimpleInvoice

/**
  * @author MyCollab Ltd
  * @since 5.2.10
  */
object InvoiceEvent {

  class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class NewInvoiceAdded(source: AnyRef, data: SimpleInvoice) extends ApplicationEvent(source, data) {}

  class InvoiceUpdateAdded(source: AnyRef, data: SimpleInvoice) extends ApplicationEvent(source, data) {}

  class InvoiceDelete(source: AnyRef, data: SimpleInvoice) extends ApplicationEvent(source, data) {}

  class DisplayInvoiceView(source: AnyRef, data: SimpleInvoice) extends ApplicationEvent(source, data) {}

}
