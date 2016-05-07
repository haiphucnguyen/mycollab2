package com.esofthead.mycollab.module.crm.view.setting

import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.crm.events.CrmSettingEvent
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver

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
