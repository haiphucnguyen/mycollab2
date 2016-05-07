package com.esofthead.mycollab.module.crm.view.file

import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.crm.events.DocumentEvent
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver

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
