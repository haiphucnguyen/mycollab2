package com.mycollab.mobile.module.crm.view.contact

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.mobile.module.crm.events.{ContactEvent, CrmEvent}
import com.mycollab.mobile.module.crm.{CrmModuleScreenData, CrmUrlResolver}
import com.mycollab.module.crm.domain.Contact
import com.mycollab.module.crm.i18n.ContactI18nEnum
import com.mycollab.vaadin.AppContext

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class ContactUrlResolver extends CrmUrlResolver {
  this.addSubResolver("list", new ContactListUrlResolver)
  this.addSubResolver("preview", new ContactPreviewUrlResolver)
  this.addSubResolver("add", new ContactAddUrlResolver)
  this.addSubResolver("edit", new ContactEditUrlResolver)
  
  class ContactListUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new CrmEvent.GotoContainer(this,
        new CrmModuleScreenData.GotoModule(AppContext.getMessage(ContactI18nEnum.LIST))))
    }
  }
  
  class ContactAddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new ContactEvent.GotoAdd(this, new Contact))
    }
  }
  
  class ContactEditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val contactId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new ContactEvent.GotoEdit(this, contactId))
    }
  }
  
  class ContactPreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val contactId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new ContactEvent.GotoRead(this, contactId))
    }
  }
  
}
