package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.SimpleInvoice

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class InvoiceEvent {
    class GotoList(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class NewInvoiceAdded(source: Any, data: SimpleInvoice) : ApplicationEvent(source, data) 

    class InvoiceUpdateAdded(source: Any, data: SimpleInvoice) : ApplicationEvent(source, data) 

    class InvoiceDelete(source: Any, data: SimpleInvoice) : ApplicationEvent(source, data) 

    class DisplayInvoiceView(source: Any, data: SimpleInvoice) : ApplicationEvent(source, data) 
}