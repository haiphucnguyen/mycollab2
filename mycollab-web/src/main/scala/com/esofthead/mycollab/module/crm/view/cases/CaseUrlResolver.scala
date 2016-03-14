package com.esofthead.mycollab.module.crm.view.cases

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.crm.domain.Account
import com.esofthead.mycollab.module.crm.events.CaseEvent
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver

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
      EventBusFactory.getInstance.post(new CaseEvent.GotoList(this, null))
    }
  }

  class CaseAddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance.post(new CaseEvent.GotoAdd(this, new Account))
    }
  }

  class CaseEditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val caseId = new UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance.post(new CaseEvent.GotoEdit(this, caseId))
    }
  }

  class CasePreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val caseId = new UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance.post(new CaseEvent.GotoRead(this, caseId))
    }
  }

}
