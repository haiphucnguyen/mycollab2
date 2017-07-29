package com.mycollab.module.crm.httpmapping

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.crm.domain.Account
import com.mycollab.module.crm.event.CampaignEvent

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
      EventBusFactory.getInstance().post(new CampaignEvent.GotoList(this, null))
    }
  }

  class AddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new CampaignEvent.GotoAdd(this, new Account))
    }
  }

  class EditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val campaignId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new CampaignEvent.GotoEdit(this, campaignId))
    }
  }

  class PreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val campaignId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new CampaignEvent.GotoRead(this, campaignId))
    }
  }

}
