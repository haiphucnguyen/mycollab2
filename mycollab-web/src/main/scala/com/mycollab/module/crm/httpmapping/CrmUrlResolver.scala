package com.mycollab.module.crm.httpmapping

import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.crm.event.CrmEvent
import com.mycollab.shell.events.ShellEvent
import com.mycollab.vaadin.mvp.UrlResolver
import com.mycollab.vaadin.web.ui.ModuleHelper

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class CrmUrlResolver extends UrlResolver {
  def build: UrlResolver = {
    this.addSubResolver("dashboard", new CrmDashboardUrlResolver)
    this.addSubResolver("account", new AccountUrlResolver)
    this.addSubResolver("contact", new ContactUrlResolver)
    this.addSubResolver("campaign", new CampaignUrlResolver)
    this.addSubResolver("lead", new LeadUrlResolver)
    this.addSubResolver("opportunity", new OpportunityUrlResolver)
    this.addSubResolver("cases", new CaseUrlResolver)
    this.addSubResolver("activity", new ActivityUrlResolver)
    this.addSubResolver("file", new FileUrlResolver)
    this.addSubResolver("setting", new CrmSettingUrlResolver)
    this
  }

  override def handle(params: String*) {
    if (!ModuleHelper.isCurrentCrmModule) {
      EventBusFactory.getInstance().post(new ShellEvent.GotoCrmModule(this, params))
    }
    else {
      super.handle(params: _*)
    }
  }

  protected def defaultPageErrorHandler() {
    EventBusFactory.getInstance().post(new ShellEvent.GotoCrmModule(this, null))
  }

  class CrmDashboardUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new CrmEvent.GotoHome(this, null))
    }
  }

}
