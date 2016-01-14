package com.esofthead.mycollab.mobile.shell.view

import com.esofthead.mycollab.core.utils.ScalaUtils
import com.esofthead.mycollab.eventmanager.ApplicationEventListener
import com.esofthead.mycollab.mobile.MobileApplication
import com.esofthead.mycollab.mobile.module.crm.view.CrmModulePresenter
import com.esofthead.mycollab.mobile.module.project.ProjectModuleScreenData
import com.esofthead.mycollab.mobile.module.project.view.ProjectModulePresenter
import com.esofthead.mycollab.mobile.module.user.view.LoginPresenter
import com.esofthead.mycollab.mobile.shell.events.ShellEvent
import com.esofthead.mycollab.mobile.ui.IMobileView
import com.esofthead.mycollab.vaadin.mvp.{AbstractController, PresenterResolver}
import com.google.common.eventbus.Subscribe
import com.vaadin.addon.touchkit.ui.NavigationManager
import com.vaadin.ui.{Component, UI}

/**
  * @author MyCollab Ltd
  * @since 5.2.5
  */
class ShellController(val mainNav: NavigationManager) extends AbstractController {
  bind()

  private def bind(): Unit = {
    this.register(new ApplicationEventListener[ShellEvent.GotoLoginView]() {
      @Subscribe def handle(event: ShellEvent.GotoLoginView) {
        val presenter = PresenterResolver.getPresenter(classOf[LoginPresenter])
        presenter.go(mainNav, null)
      }
    })

    this.register(new ApplicationEventListener[ShellEvent.LogOut]() {
      @Subscribe def handle(event: ShellEvent.LogOut) {
        (UI.getCurrent.asInstanceOf[MobileApplication]).redirectToLoginView
      }
    })

    this.register(new ApplicationEventListener[ShellEvent.GotoMainPage]() {
      @Subscribe def handle(event: ShellEvent.GotoMainPage) {
        val presenter = PresenterResolver.getPresenter(classOf[MainViewPresenter])
        presenter.go(mainNav, null)
      }
    })
    this.register(new ApplicationEventListener[ShellEvent.GotoCrmModule]() {
      @Subscribe def handle(event: ShellEvent.GotoCrmModule) {
        val presenter = PresenterResolver.getPresenter(classOf[CrmModulePresenter])
        presenter.go(mainNav, null)
      }
    })
    this.register(new ApplicationEventListener[ShellEvent.GotoProjectModule]() {
      @Subscribe def handle(event: ShellEvent.GotoProjectModule) {
        val presenter = PresenterResolver.getPresenter(classOf[ProjectModulePresenter])
        val screenData = new ProjectModuleScreenData.GotoModule(ScalaUtils.stringConvertSeqToArray(event.getData))
        presenter.go(mainNav, screenData)
      }
    })

    this.register(new ApplicationEventListener[ShellEvent.PushView]() {
      @Subscribe def handle(event: ShellEvent.PushView) {
        if (event.getData.isInstanceOf[Component]) {
          if (event.getData.isInstanceOf[IMobileView]) {
            (event.getData.asInstanceOf[IMobileView]).setPreviousComponent(mainNav.getCurrentComponent)
          }
          mainNav.navigateTo(event.getData.asInstanceOf[Component])
        }
      }
    })

    this.register(new ApplicationEventListener[ShellEvent.NavigateBack]() {
      @Subscribe def handle(event: ShellEvent.NavigateBack) {
        mainNav.navigateBack
      }
    })
  }
}
