package com.mycollab.mobile.module.crm.view.activity

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.mobile.module.crm.CrmUrlResolver
import com.mycollab.mobile.module.crm.events.ActivityEvent
import com.mycollab.module.crm.domain.CallWithBLOBs

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class CallUrlResolver extends CrmUrlResolver {
  this.addSubResolver("add", new CallAddUrlResolver)
  this.addSubResolver("edit", new CallEditUrlResolver)
  this.addSubResolver("preview", new CallPreviewUrlResolver)
  
  class CallAddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new ActivityEvent.CallAdd(this, new CallWithBLOBs))
    }
  }
  
  class CallEditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val meetingId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new ActivityEvent.CallEdit(this, meetingId))
    }
  }
  
  class CallPreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val accountId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new ActivityEvent.CallRead(this, accountId))
    }
  }
  
}
