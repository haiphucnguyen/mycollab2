package com.mycollab.module.crm.httpmapping

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.crm.domain.Account
import com.mycollab.module.crm.event.AccountEvent

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class AccountUrlResolver extends CrmUrlResolver {
  this.addSubResolver("list", new AccountListUrlResolver)
  this.addSubResolver("preview", new AccountPreviewUrlResolver)
  this.addSubResolver("add", new AccountAddUrlResolver)
  this.addSubResolver("edit", new AccountEditUrlResolver)

  private class AccountListUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new AccountEvent.GotoList(this, null))
    }
  }

  private class AccountAddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new AccountEvent.GotoAdd(this, new Account))
    }
  }

  private class AccountEditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val accountId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new AccountEvent.GotoEdit(this, accountId))
    }
  }

  private class AccountPreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val accountId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new AccountEvent.GotoRead(this, accountId))
    }
  }

}
