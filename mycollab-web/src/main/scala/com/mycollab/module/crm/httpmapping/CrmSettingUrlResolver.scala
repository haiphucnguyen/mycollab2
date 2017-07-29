package com.mycollab.module.crm.httpmapping

import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.crm.event.CrmSettingEvent

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class CrmSettingUrlResolver extends CrmUrlResolver {
  this.addSubResolver("notification", new NotificationSettingUrlResolver)
  this.addSubResolver("customlayout", new CustomLayoutUrlResolver)

  class NotificationSettingUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new CrmSettingEvent.GotoNotificationSetting(this, null))
    }
  }

  class CustomLayoutUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new CrmSettingEvent.GotoCustomViewSetting(this, null))
    }
  }

}
