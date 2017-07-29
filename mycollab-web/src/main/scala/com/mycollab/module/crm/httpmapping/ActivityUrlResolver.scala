package com.mycollab.module.crm.httpmapping

import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.crm.event.ActivityEvent

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class ActivityUrlResolver extends CrmUrlResolver {
  this.addSubResolver("calendar", new ActivityCalendartUrlResolver)
  this.addSubResolver("todo", new ActivityTodoAddUrlResolver)
  this.addSubResolver("task", new ActivityTaskUrlResolver)
  this.addSubResolver("meeting", new MeetingUrlResolver)
  this.addSubResolver("call", new CallUrlResolver)

  class ActivityCalendartUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new ActivityEvent.GotoCalendar(this, null))
    }
  }

  class ActivityTodoAddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new ActivityEvent.GotoTodoList(this, null))
    }
  }

}
