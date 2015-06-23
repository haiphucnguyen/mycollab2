package com.esofthead.mycollab.premium.module.user.accountsettings.view

import com.esofthead.mycollab.eventmanager.ApplicationEventListener
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModule
import com.esofthead.mycollab.premium.module.user.accountsettings.customize.view.CustomizePresenter
import com.esofthead.mycollab.premium.module.user.accountsettings.view.events.SettingExtEvent
import com.esofthead.mycollab.premium.module.user.accountsettings.view.parameters.SettingExtScreenData.Customize
import com.esofthead.mycollab.vaadin.mvp.{AbstractController, PresenterResolver}
import com.google.common.eventbus.Subscribe

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
class UserAccountExtController(container: AccountModule) extends AbstractController {
    bingSettingEvents()

    private def bingSettingEvents(): Unit = {
        this.register(new ApplicationEventListener[SettingExtEvent.GotoCustomizePage]() {
            @Subscribe def handle(event: SettingExtEvent.GotoCustomizePage) {
                val presenter = PresenterResolver.getPresenter(classOf[CustomizePresenter])
                presenter.go(container, new Customize())
            }
        })
    }
}
