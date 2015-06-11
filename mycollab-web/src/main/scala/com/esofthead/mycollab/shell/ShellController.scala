package com.esofthead.mycollab.shell

import com.esofthead.mycollab.eventmanager.ApplicationEventListener
import com.esofthead.mycollab.module.user.view.ForgotPasswordPresenter
import com.esofthead.mycollab.shell.events.ShellEvent
import com.esofthead.mycollab.shell.view.{MainView, MainViewPresenter, MainWindowContainer}
import com.esofthead.mycollab.vaadin.mvp.{PresenterResolver, AbstractController}
import com.esofthead.mycollab.web.DesktopApplication
import com.google.common.eventbus.Subscribe
import com.vaadin.ui.UI

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class ShellController(container: MainWindowContainer) extends AbstractController {
    bind()

    private def bind() {
        this.register(new ApplicationEventListener[ShellEvent.GotoMainPage]() {
            @Subscribe def handle(event: ShellEvent.GotoMainPage) {
                val mainViewPresenter: MainViewPresenter = PresenterResolver.getPresenter(classOf[MainViewPresenter])
                val mainView: MainView = mainViewPresenter.getView
                container.setContent(mainView)
                container.setStyleName("mainView")
                mainViewPresenter.go(container, null)
            }
        })
        this.register(new ApplicationEventListener[ShellEvent.LogOut]() {
            @Subscribe def handle(event: ShellEvent.LogOut) {
                (UI.getCurrent.asInstanceOf[DesktopApplication]).redirectToLoginView
            }
        })
        this.register(new ApplicationEventListener[ShellEvent.GotoForgotPasswordPage]() {
            @Subscribe def handle(event: ShellEvent.GotoForgotPasswordPage) {
                val presenter: ForgotPasswordPresenter = PresenterResolver.getPresenter(classOf[ForgotPasswordPresenter])
                presenter.go(container, null)
            }
        })
    }
}
