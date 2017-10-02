package com.mycollab.module.project.event

import com.mycollab.vaadin.event.ApplicationEvent
import com.mycollab.module.project.domain.SimpleInvoice

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object InvoiceEvent {
    class GotoList(source: Any, val data: Any?) : ApplicationEvent(source)

    class NewInvoiceAdded(source: Any, data: SimpleInvoice) : ApplicationEvent(source)

    class InvoiceUpdateAdded(source: Any, data: SimpleInvoice) : ApplicationEvent(source)

    class InvoiceDelete(source: Any, data: SimpleInvoice) : ApplicationEvent(source)

    class DisplayInvoiceView(source: Any, data: SimpleInvoice) : ApplicationEvent(source)
}