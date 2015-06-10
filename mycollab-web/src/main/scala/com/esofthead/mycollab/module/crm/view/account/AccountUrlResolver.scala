package com.esofthead.mycollab.module.crm.view.account

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.crm.domain.Account
import com.esofthead.mycollab.module.crm.events.AccountEvent
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver

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
            EventBusFactory.getInstance.post(new AccountEvent.GotoList(this, null))
        }
    }

    private class AccountAddUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new AccountEvent.GotoAdd(this, new Account))
        }
    }

    private class AccountEditUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            val accountId: Integer = new UrlTokenizer(params(0)).getInt
            EventBusFactory.getInstance.post(new AccountEvent.GotoEdit(this, accountId))
        }
    }

    private class AccountPreviewUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            val accountId: Integer = new UrlTokenizer(params(0)).getInt
            EventBusFactory.getInstance.post(new AccountEvent.GotoRead(this, accountId))
        }
    }

}
