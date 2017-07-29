package com.mycollab.shell

import com.mycollab.eventmanager.ApplicationEventListener
import com.mycollab.shell.events.ShellEvent
import com.mycollab.shell.view.{ForgotPasswordPresenter, MainViewPresenter, MainWindowContainer}
import com.mycollab.vaadin.mvp.{AbstractController, PresenterResolver}
import com.mycollab.web.DesktopApplication
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
        val mainViewPresenter = PresenterResolver.getPresenter(classOf[MainViewPresenter])
        val mainView = mainViewPresenter.getView
        container.setContent(mainView)
        container.setStyleName("mainView")
        mainViewPresenter.go(container, null)
      }
    })
    this.register(new ApplicationEventListener[ShellEvent.LogOut]() {
      @Subscribe def handle(event: ShellEvent.LogOut) {
        (UI.getCurrent.asInstanceOf[DesktopApplication]).redirectToLoginView()
      }
    })
    this.register(new ApplicationEventListener[ShellEvent.GotoForgotPasswordPage]() {
      @Subscribe def handle(event: ShellEvent.GotoForgotPasswordPage) {
        val presenter = PresenterResolver.getPresenter(classOf[ForgotPasswordPresenter])
        presenter.go(container, null)
      }
    })
  }
}
