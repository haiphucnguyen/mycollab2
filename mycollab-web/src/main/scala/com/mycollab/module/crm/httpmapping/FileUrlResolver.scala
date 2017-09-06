package com.mycollab.module.crm.httpmapping

import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.crm.event.DocumentEvent

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class FileUrlResolver extends CrmUrlResolver {
  this.addSubResolver("dashboard", new FileDashboardUrlResolver)

  class FileDashboardUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new DocumentEvent.GotoDashboard(this, null))
    }
  }

}
