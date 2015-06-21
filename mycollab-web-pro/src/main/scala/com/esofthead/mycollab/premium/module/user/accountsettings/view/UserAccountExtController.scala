package com.esofthead.mycollab.premium.module.user.accountsettings.view

import com.esofthead.mycollab.eventmanager.ApplicationEventListener
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModule
import com.esofthead.mycollab.premium.module.user.accountsettings.customize.view.GeneralSettingPresenter
import com.esofthead.mycollab.premium.module.user.accountsettings.view.events.SettingExtEvent
import com.esofthead.mycollab.premium.module.user.accountsettings.view.parameters.SettingExtScreenData
import com.esofthead.mycollab.premium.module.user.accountsettings.view.parameters.SettingExtScreenData.GeneralSettingEdit
import com.esofthead.mycollab.vaadin.mvp.{AbstractController, PresenterResolver}
import com.google.common.eventbus.Subscribe

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
class UserAccountExtController(container: AccountModule) extends AbstractController {
    bingSettingEvents()

    private def bingSettingEvents(): Unit = {
        this.register(new ApplicationEventListener[SettingExtEvent.GeneralSettingRead]() {
            @Subscribe def handle(event: SettingExtEvent.GeneralSettingRead) {
                val presenter = PresenterResolver.getPresenter(classOf[GeneralSettingPresenter])
                presenter.go(container, new SettingExtScreenData.GeneralSettingRead())
            }
        })

        this.register(new ApplicationEventListener[SettingExtEvent.GeneralSettingEdit]() {
            @Subscribe def handle(event: SettingExtEvent.GeneralSettingEdit) {
                val presenter = PresenterResolver.getPresenter(classOf[GeneralSettingPresenter])
                presenter.go(container, new GeneralSettingEdit())
            }
        })
    }
}
