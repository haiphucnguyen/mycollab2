package com.esofthead.mycollab.module.crm.view.activity

import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.crm.events.ActivityEvent
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver

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
