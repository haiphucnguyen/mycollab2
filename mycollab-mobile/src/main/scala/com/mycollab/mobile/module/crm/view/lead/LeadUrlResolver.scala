package com.mycollab.mobile.module.crm.view.lead

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.mobile.module.crm.events.{CrmEvent, LeadEvent}
import com.mycollab.mobile.module.crm.{CrmModuleScreenData, CrmUrlResolver}
import com.mycollab.module.crm.domain.Lead
import com.mycollab.module.crm.i18n.LeadI18nEnum
import com.mycollab.vaadin.AppContext

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class LeadUrlResolver extends CrmUrlResolver {
  this.addSubResolver("list", new LeadListUrlResolver)
  this.addSubResolver("preview", new LeadPreviewUrlResolver)
  this.addSubResolver("add", new LeadAddUrlResolver)
  this.addSubResolver("edit", new LeadEditUrlResolver)
  
  class LeadListUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new CrmEvent.GotoContainer(this,
        new CrmModuleScreenData.GotoModule(AppContext.getMessage(LeadI18nEnum.LIST))))
    }
  }
  
  class LeadAddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new LeadEvent.GotoAdd(this, new Lead))
    }
  }
  
  class LeadEditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val leadId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new LeadEvent.GotoEdit(this, leadId))
    }
  }
  
  class LeadPreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val leadId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new LeadEvent.GotoRead(this, leadId))
    }
  }
  
}
