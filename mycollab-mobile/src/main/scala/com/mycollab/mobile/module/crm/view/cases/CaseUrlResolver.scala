package com.mycollab.mobile.module.crm.view.cases

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.mobile.module.crm.events.{CaseEvent, CrmEvent}
import com.mycollab.mobile.module.crm.{CrmModuleScreenData, CrmUrlResolver}
import com.mycollab.module.crm.domain.Account

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class CaseUrlResolver extends CrmUrlResolver {
  this.addSubResolver("list", new CaseListUrlResolver)
  this.addSubResolver("add", new CaseAddUrlResolver)
  this.addSubResolver("edit", new CaseEditUrlResolver)
  this.addSubResolver("preview", new CasePreviewUrlResolver)

  class CaseListUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new CrmEvent.GotoActivitiesView(this,
        new CrmModuleScreenData.GotoModule(Array())))
    }
  }

  class CaseAddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new CaseEvent.GotoAdd(this, new Account))
    }
  }

  class CaseEditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val caseId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new CaseEvent.GotoEdit(this, caseId))
    }
  }

  class CasePreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val caseId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new CaseEvent.GotoRead(this, caseId))
    }
  }

}
