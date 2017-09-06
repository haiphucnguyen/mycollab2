package com.mycollab.module.crm.httpmapping

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.crm.domain.Lead
import com.mycollab.module.crm.event.LeadEvent

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
      EventBusFactory.getInstance().post(new LeadEvent.GotoList(this, null))
    }
  }

  class AddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new LeadEvent.GotoAdd(this, new Lead))
    }
  }

  class EditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val leadId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new LeadEvent.GotoEdit(this, leadId))
    }
  }

  class PreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val leadId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new LeadEvent.GotoRead(this, leadId))
    }
  }

}
