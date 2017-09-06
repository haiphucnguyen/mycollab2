package com.mycollab.module.user.accountsettings.view

import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.user.accountsettings.view.events.AccountBillingEvent
import com.mycollab.premium.module.user.accountsettings.view.AccountSettingUrlResolver
import com.mycollab.vaadin.mvp.UrlResolver

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
class BillingUrlResolver extends AccountSettingUrlResolver {
  this.defaultUrlResolver = new SummaryUrlResolver
  this.addSubResolver("history", new HistoryUrlResolver)
  this.addSubResolver("cancel", new CancelUrlResolver)

  private class SummaryUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new AccountBillingEvent.GotoSummary(this, null))
    }
  }

  private class HistoryUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new AccountBillingEvent.GotoHistory(this, null))
    }
  }

  private class CancelUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new AccountBillingEvent.CancelAccount(this, null))
    }
  }

  override protected def defaultPageErrorHandler(): Unit = {
    handlePage()
  }
}