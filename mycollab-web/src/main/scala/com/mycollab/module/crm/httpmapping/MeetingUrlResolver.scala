package com.mycollab.module.crm.httpmapping

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.crm.domain.MeetingWithBLOBs
import com.mycollab.module.crm.event.ActivityEvent

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class MeetingUrlResolver extends CrmUrlResolver {
  this.addSubResolver("add", new MeetingAddUrlResolver)
  this.addSubResolver("edit", new MeetingEditUrlResolver)
  this.addSubResolver("preview", new MeetingPreviewUrlResolver)

  class MeetingAddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new ActivityEvent.MeetingAdd(this, new MeetingWithBLOBs))
    }
  }

  class MeetingEditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val meetingId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new ActivityEvent.MeetingEdit(this, meetingId))
    }
  }

  class MeetingPreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val meetingId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new ActivityEvent.MeetingRead(this, meetingId))
    }
  }

}
