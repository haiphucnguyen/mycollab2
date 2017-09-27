package com.mycollab.mobile.module.crm.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class OpportunityEvent {
    class Save(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class Search(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoList(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoAdd(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoEdit(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoRead(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoRelatedItems(source: Any, data: Any?) : ApplicationEvent(source, data) 
}