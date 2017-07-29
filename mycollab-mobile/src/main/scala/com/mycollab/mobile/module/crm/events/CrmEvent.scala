package com.mycollab.mobile.module.crm.events

import com.mycollab.events.ApplicationEvent

/**
  * @author MyCollab Ltd
  * @since 5.4.9
  */
object CrmEvent {

  class GotoActivitiesView(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
