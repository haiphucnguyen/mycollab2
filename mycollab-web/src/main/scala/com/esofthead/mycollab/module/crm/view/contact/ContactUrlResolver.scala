package com.esofthead.mycollab.module.crm.view.contact

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.crm.domain.Contact
import com.esofthead.mycollab.module.crm.events.ContactEvent
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class ContactUrlResolver extends CrmUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)

  class ListUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance.post(new ContactEvent.GotoList(this, null))
    }
  }

  class AddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance.post(new ContactEvent.GotoAdd(this, new Contact))
    }
  }

  class EditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val contactId = new UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance.post(new ContactEvent.GotoEdit(this, contactId))
    }
  }

  class PreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val contactId = new UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance.post(new ContactEvent.GotoRead(this, contactId))
    }
  }

}
