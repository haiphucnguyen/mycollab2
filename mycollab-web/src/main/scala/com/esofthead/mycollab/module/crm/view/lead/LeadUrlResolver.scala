package com.esofthead.mycollab.module.crm.view.lead

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.crm.domain.Lead
import com.esofthead.mycollab.module.crm.events.LeadEvent
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class LeadUrlResolver extends CrmUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)

  class ListUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance.post(new LeadEvent.GotoList(this, null))
    }
  }

  class AddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance.post(new LeadEvent.GotoAdd(this, new Lead))
    }
  }

  class EditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val leadId = new UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance.post(new LeadEvent.GotoEdit(this, leadId))
    }
  }

  class PreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val leadId = new UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance.post(new LeadEvent.GotoRead(this, leadId))
    }
  }

}
