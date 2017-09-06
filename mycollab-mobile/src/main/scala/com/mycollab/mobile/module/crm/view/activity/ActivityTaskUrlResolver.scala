package com.mycollab.mobile.module.crm.view.activity

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.mobile.module.crm.CrmUrlResolver
import com.mycollab.mobile.module.crm.events.ActivityEvent
import com.mycollab.module.crm.domain.CrmTask

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class ActivityTaskUrlResolver extends CrmUrlResolver {
  this.addSubResolver("add", new TaskAddUrlResolver)
  this.addSubResolver("edit", new TaskEditUrlResolver)
  this.addSubResolver("preview", new TaskPreviewUrlResolver)
  
  class TaskAddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new ActivityEvent.TaskAdd(this, new CrmTask))
    }
  }
  
  class TaskEditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val meetingId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new ActivityEvent.TaskEdit(this, meetingId))
    }
  }
  
  class TaskPreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val accountId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new ActivityEvent.TaskRead(this, accountId))
    }
  }
  
}
