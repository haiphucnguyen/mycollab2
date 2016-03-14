package com.esofthead.mycollab.shell.view

import com.esofthead.mycollab.core.utils.ScalaUtils
import com.esofthead.mycollab.eventmanager.ApplicationEventListener
import com.esofthead.mycollab.module.crm.view.{CrmModulePresenter, CrmModuleScreenData}
import com.esofthead.mycollab.module.file.view.{FileModuleScreenData, IFileModulePresenter}
import com.esofthead.mycollab.module.project.view.ProjectModulePresenter
import com.esofthead.mycollab.module.project.view.parameters.ProjectModuleScreenData
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModulePresenter
import com.esofthead.mycollab.premium.module.user.accountsettings.view.AccountModuleScreenData
import com.esofthead.mycollab.shell.events.ShellEvent
import com.esofthead.mycollab.vaadin.mvp.{AbstractController, PresenterResolver}
import com.google.common.eventbus.Subscribe

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class MainViewController(val container: MainView) extends AbstractController {
  bind()

  private def bind(): Unit = {
    this.register(new ApplicationEventListener[ShellEvent.GotoCrmModule]() {
      @Subscribe def handle(event: ShellEvent.GotoCrmModule) {
        val crmModulePresenter = PresenterResolver.getPresenter(classOf[CrmModulePresenter])
        val screenData = new CrmModuleScreenData.GotoModule(ScalaUtils.stringConvertSeqToArray(event.getData))
        crmModulePresenter.go(container, screenData)
      }
    })
    this.register(new ApplicationEventListener[ShellEvent.GotoProjectModule]() {
      @Subscribe def handle(event: ShellEvent.GotoProjectModule) {
        val prjPresenter = PresenterResolver.getPresenter(classOf[ProjectModulePresenter])
        val screenData = new ProjectModuleScreenData.GotoModule(ScalaUtils.stringConvertSeqToArray(event.getData))
        prjPresenter.go(container, screenData)
      }
    })
    this.register(new ApplicationEventListener[ShellEvent.GotoUserAccountModule]() {
      @Subscribe def handle(event: ShellEvent.GotoUserAccountModule) {
        val presenter = PresenterResolver.getPresenter(classOf[AccountModulePresenter])
        presenter.go(container, new AccountModuleScreenData.GotoModule(ScalaUtils.stringConvertSeqToArray(event.getData)))
      }
    })
    this.register(new ApplicationEventListener[ShellEvent.GotoFileModule]() {
      @Subscribe def handle(event: ShellEvent.GotoFileModule) {
        val fileModulePresenter = PresenterResolver.getPresenter(classOf[IFileModulePresenter])
        val screenData = new FileModuleScreenData.GotoModule(ScalaUtils.stringConvertSeqToArray(event.getData))
        fileModulePresenter.go(container, screenData)
      }
    })
  }
}
