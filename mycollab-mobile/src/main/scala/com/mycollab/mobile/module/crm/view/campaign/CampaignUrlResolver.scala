package com.mycollab.mobile.module.crm.view.campaign

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.mobile.module.crm.events.{CampaignEvent, CrmEvent}
import com.mycollab.mobile.module.crm.{CrmModuleScreenData, CrmUrlResolver}
import com.mycollab.module.crm.domain.Account
import com.mycollab.module.crm.i18n.CampaignI18nEnum
import com.mycollab.vaadin.UserUIContext

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class CampaignUrlResolver extends CrmUrlResolver {
  this.addSubResolver("list", new CampaignListUrlResolver)
  this.addSubResolver("add", new CampaignAddUrlResolver)
  this.addSubResolver("edit", new CampaignEditUrlResolver)
  this.addSubResolver("preview", new CampaignPreviewUrlResolver)
  
  class CampaignListUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new CrmEvent.GotoActivitiesView(this,
        new CrmModuleScreenData.GotoModule(Array())))
    }
  }
  
  class CampaignAddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new CampaignEvent.GotoAdd(this, new Account))
    }
  }
  
  class CampaignEditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val campaignId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new CampaignEvent.GotoEdit(this, campaignId))
    }
  }
  
  class CampaignPreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val campaignId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new CampaignEvent.GotoRead(this, campaignId))
    }
  }
  
}
