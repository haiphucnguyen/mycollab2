package com.mycollab.mobile.module.crm.events

import com.mycollab.events.ApplicationEvent

/**
  * @author MyCollab Ltd
  * @since 5.4.9
  */
object OpportunityEvent {
  class Save(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class Search(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoRelatedItems(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
}
