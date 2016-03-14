package com.esofthead.mycollab.premium.module.user.accountsettings.view

import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.user.accountsettings.view.events.SettingEvent
import com.esofthead.mycollab.module.user.accountsettings.view.events.SettingEvent.GotoTheme

/**
  * @author MyCollab Ltd
  * @since 5.1.0
  */
class SettingUrlResolver extends AccountSettingUrlResolver {
  this.addSubResolver("general", new GeneralUrlResolver)
  this.addSubResolver("theme", new ThemeUrlResolver)

  private class GeneralUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance.post(new SettingEvent.GotoGeneralSetting(this, null))
    }
  }

  private class ThemeUrlResolver extends AccountSettingUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance.post(new GotoTheme(SettingUrlResolver.this, null))
    }
  }

}
