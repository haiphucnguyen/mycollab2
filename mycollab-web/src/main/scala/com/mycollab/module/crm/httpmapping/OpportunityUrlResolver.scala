package com.mycollab.module.crm.httpmapping

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.crm.domain.Account
import com.mycollab.module.crm.event.OpportunityEvent

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class OpportunityUrlResolver extends CrmUrlResolver {
  this.addSubResolver("list", new OpportunityListUrlResolver)
  this.addSubResolver("add", new OpportunityAddUrlResolver)
  this.addSubResolver("edit", new OpportunityEditUrlResolver)
  this.addSubResolver("preview", new OpportunityPreviewUrlResolver)

  class OpportunityListUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new OpportunityEvent.GotoList(this, null))
    }
  }

  class OpportunityAddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new OpportunityEvent.GotoAdd(this, new Account))
    }
  }

  class OpportunityEditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val opportunityId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new OpportunityEvent.GotoEdit(this, opportunityId))
    }
  }

  class OpportunityPreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val opportunityId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new OpportunityEvent.GotoRead(this, opportunityId))
    }
  }

}
