package com.mycollab.mobile.module.crm.view.opportunity

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.mobile.module.crm.events.{CrmEvent, OpportunityEvent}
import com.mycollab.mobile.module.crm.{CrmModuleScreenData, CrmUrlResolver}
import com.mycollab.module.crm.domain.Account

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
      EventBusFactory.getInstance().post(new CrmEvent.GotoActivitiesView(this,
        new CrmModuleScreenData.GotoModule(Array())))
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
