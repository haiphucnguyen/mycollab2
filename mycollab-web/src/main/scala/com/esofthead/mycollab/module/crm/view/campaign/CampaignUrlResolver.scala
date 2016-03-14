package com.esofthead.mycollab.module.crm.view.campaign

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.crm.domain.Account
import com.esofthead.mycollab.module.crm.events.CampaignEvent
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class CampaignUrlResolver extends CrmUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)

  class ListUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance.post(new CampaignEvent.GotoList(this, null))
    }
  }

  class AddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance.post(new CampaignEvent.GotoAdd(this, new Account))
    }
  }

  class EditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val campaignId = new UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance.post(new CampaignEvent.GotoEdit(this, campaignId))
    }
  }

  class PreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val campaignId = new UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance.post(new CampaignEvent.GotoRead(this, campaignId))
    }
  }

}
