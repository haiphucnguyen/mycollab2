package com.esofthead.mycollab.mobile.shell.view

import com.esofthead.mycollab.configuration.PasswordEncryptHelper
import com.esofthead.mycollab.core.MyCollabException
import com.esofthead.mycollab.eventmanager.{ApplicationEventListener, EventBusFactory}
import com.esofthead.mycollab.mobile.MobileApplication
import com.esofthead.mycollab.mobile.module.crm.view.CrmModulePresenter
import com.esofthead.mycollab.mobile.module.project.view.ProjectModulePresenter
import com.esofthead.mycollab.mobile.module.user.events.UserEvent
import com.esofthead.mycollab.mobile.module.user.view.LoginPresenter
import com.esofthead.mycollab.mobile.shell.events.ShellEvent
import com.esofthead.mycollab.mobile.ui.IMobileView
import com.esofthead.mycollab.module.user.domain.{SimpleBillingAccount, SimpleUser}
import com.esofthead.mycollab.module.user.service.{BillingAccountService, UserService}
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.esofthead.mycollab.vaadin.AppContext
import com.esofthead.mycollab.vaadin.mvp.{AbstractController, PresenterResolver}
import com.google.common.eventbus.Subscribe
import com.vaadin.addon.touchkit.extensions.LocalStorage
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
        val presenter: LoginPresenter = PresenterResolver.getPresenter(classOf[LoginPresenter])
        presenter.go(mainNav, null)
      }
    })

    this.register(new ApplicationEventListener[ShellEvent.LogOut]() {
      @Subscribe def handle(event: ShellEvent.LogOut) {
        (UI.getCurrent.asInstanceOf[MobileApplication]).redirectToLoginView
      }
    })

    this.register(new ApplicationEventListener[UserEvent.PlainLogin]() {
      @Subscribe def handle(event: UserEvent.PlainLogin) {
        val data: Array[String] = event.getData.asInstanceOf[Array[String]]
        try {
          doLogin(data(0), data(1), data(2).toBoolean)
        }
        catch {
          case exception: MyCollabException => {
            EventBusFactory.getInstance.post(new ShellEvent.GotoLoginView(this, null))
          }
        }
      }
    })
    this.register(new ApplicationEventListener[ShellEvent.GotoMainPage]() {
      @Subscribe def handle(event: ShellEvent.GotoMainPage) {
        val presenter: MainViewPresenter = PresenterResolver.getPresenter(classOf[MainViewPresenter])
        presenter.go(mainNav, null)
      }
    })
    this.register(new ApplicationEventListener[ShellEvent.GotoCrmModule]() {
      @Subscribe def handle(event: ShellEvent.GotoCrmModule) {
        val presenter: CrmModulePresenter = PresenterResolver.getPresenter(classOf[CrmModulePresenter])
        presenter.go(mainNav, null)
      }
    })
    this.register(new ApplicationEventListener[ShellEvent.GotoProjectModule]() {
      @Subscribe def handle(event: ShellEvent.GotoProjectModule) {
        val presenter: ProjectModulePresenter = PresenterResolver.getPresenter(classOf[ProjectModulePresenter])
        presenter.go(mainNav, null)
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

  @throws(classOf[MyCollabException])
  def doLogin(username: String, password: String, isRememberPassword: Boolean) {
    val userService: UserService = ApplicationContextUtil.getSpringBean(classOf[UserService])
    val user: SimpleUser = userService.authentication(username, password, AppContext.getSubDomain, false)
    val billingAccountService: BillingAccountService = ApplicationContextUtil.getSpringBean(classOf[BillingAccountService])
    val billingAccount: SimpleBillingAccount = billingAccountService.getBillingAccountById(AppContext.getAccountId)
    if (isRememberPassword) {
      val storage: LocalStorage = LocalStorage.get
      val storeVal: String = username + "$" + PasswordEncryptHelper.encryptText(password)
      storage.put(MobileApplication.NAME_COOKIE, storeVal)
    }
    AppContext.getInstance.setSessionVariables(user, billingAccount)
    EventBusFactory.getInstance.post(new ShellEvent.GotoMainPage(UI.getCurrent, null))
  }
}
