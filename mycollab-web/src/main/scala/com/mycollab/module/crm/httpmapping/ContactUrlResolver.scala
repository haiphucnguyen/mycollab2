package com.mycollab.module.crm.httpmapping

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.crm.domain.Contact
import com.mycollab.module.crm.event.ContactEvent

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
      EventBusFactory.getInstance().post(new ContactEvent.GotoList(this, null))
    }
  }

  class AddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new ContactEvent.GotoAdd(this, new Contact))
    }
  }

  class EditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val contactId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new ContactEvent.GotoEdit(this, contactId))
    }
  }

  class PreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val contactId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new ContactEvent.GotoRead(this, contactId))
    }
  }

}
