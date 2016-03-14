package com.esofthead.mycollab.premium.module.user.accountsettings.view

import com.esofthead.mycollab.configuration.SiteConfiguration
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.user.accountsettings.view.events.{AccountBillingEvent, ProfileEvent, SetupEvent}
import com.esofthead.mycollab.shell.events.ShellEvent
import com.esofthead.mycollab.vaadin.mvp.UrlResolver
import com.esofthead.mycollab.vaadin.web.ui.ModuleHelper

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class AccountSettingUrlResolver extends UrlResolver {
  def build: UrlResolver = {
    this.addSubResolver("preview", new ReadUrlResolver)
    this.addSubResolver("billing", new BillingUrlResolver)
    this.addSubResolver("user", new UserUrlResolver)
    this.addSubResolver("role", new RoleUrlResolver)
    this.addSubResolver("setting", new SettingUrlResolver)
    if (!SiteConfiguration.isDemandEdition) {
      this.addSubResolver("setup", new SetupUrlResolver)
    }
    this
  }

  override def handle(params: String*) {
    if (!ModuleHelper.isCurrentAccountModule) {
      EventBusFactory.getInstance.post(new ShellEvent.GotoUserAccountModule(this, params))
    }
    else {
      super.handle(params: _*)
    }
  }

  protected def defaultPageErrorHandler {
    EventBusFactory.getInstance.post(new ProfileEvent.GotoProfileView(this, null))
  }

  private class ReadUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance.post(new ProfileEvent.GotoProfileView(this, null))
    }
  }

  private class BillingUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance.post(new AccountBillingEvent.GotoSummary(this, null))
    }
  }

  private class SetupUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance.post(new SetupEvent.GotoSetupPage(this, null))
    }
  }

}
