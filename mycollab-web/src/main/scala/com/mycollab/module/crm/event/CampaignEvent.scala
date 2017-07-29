package com.mycollab.module.crm.event

import com.mycollab.events.ApplicationEvent

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object CampaignEvent {

  class GotoAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class Save(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class Search(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
