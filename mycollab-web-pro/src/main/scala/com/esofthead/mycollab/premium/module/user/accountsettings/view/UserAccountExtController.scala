package com.esofthead.mycollab.premium.module.user.accountsettings.view

import com.esofthead.mycollab.eventmanager.ApplicationEventListener
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModule
import com.esofthead.mycollab.module.user.accountsettings.view.events.SettingEvent
import com.esofthead.mycollab.premium.module.user.accountsettings.customize.view.SettingPresenter
import com.esofthead.mycollab.premium.module.user.accountsettings.view.parameters.SettingExtScreenData.{GeneralSetting, ThemeCustomize}
import com.esofthead.mycollab.vaadin.mvp.{AbstractController, PresenterResolver}
import com.google.common.eventbus.Subscribe

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
class UserAccountExtController(container: AccountModule) extends AbstractController {
    bingSettingEvents()

    private def bingSettingEvents(): Unit = {
        this.register(new ApplicationEventListener[SettingEvent.GeneralSetting]() {
            @Subscribe def handle(event: SettingEvent.GeneralSetting) {
                val presenter = PresenterResolver.getPresenter(classOf[SettingPresenter])
                presenter.go(container, new GeneralSetting())
            }
        })

        this.register(new ApplicationEventListener[SettingEvent.MakeTheme]() {
            @Subscribe def handle(event: SettingEvent.MakeTheme) {
                val presenter = PresenterResolver.getPresenter(classOf[SettingPresenter])
                presenter.go(container, new ThemeCustomize())
            }
        })
    }
}
