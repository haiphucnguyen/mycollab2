package com.esofthead.mycollab.shell.view

import com.esofthead.mycollab.eventmanager.ApplicationEventListener
import com.esofthead.mycollab.module.crm.view.{CrmModuleScreenData, CrmModulePresenter}
import com.esofthead.mycollab.module.file.view.{FileModuleScreenData, IFileModulePresenter}
import com.esofthead.mycollab.module.project.view.ProjectModulePresenter
import com.esofthead.mycollab.module.project.view.parameters.ProjectModuleScreenData
import com.esofthead.mycollab.module.user.accountsettings.view.{AccountModuleScreenData, AccountModulePresenter}
import com.esofthead.mycollab.shell.events.ShellEvent
import com.esofthead.mycollab.vaadin.mvp.{PresenterResolver, AbstractController}
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
                val crmModulePresenter: CrmModulePresenter = PresenterResolver.getPresenter(classOf[CrmModulePresenter])
                val screenData: CrmModuleScreenData.GotoModule = new CrmModuleScreenData.GotoModule(event.getData.asInstanceOf[Array[String]])
                crmModulePresenter.go(container, screenData)
            }
        })
        this.register(new ApplicationEventListener[ShellEvent.GotoProjectModule]() {
            @Subscribe def handle(event: ShellEvent.GotoProjectModule) {
                val prjPresenter: ProjectModulePresenter = PresenterResolver.getPresenter(classOf[ProjectModulePresenter])
                val screenData: ProjectModuleScreenData.GotoModule = new ProjectModuleScreenData.GotoModule(event.getData.asInstanceOf[Array[String]])
                prjPresenter.go(container, screenData)
            }
        })
        this.register(new ApplicationEventListener[ShellEvent.GotoUserAccountModule]() {
            @Subscribe def handle(event: ShellEvent.GotoUserAccountModule) {
                val presenter: AccountModulePresenter = PresenterResolver.getPresenter(classOf[AccountModulePresenter])
                presenter.go(container, new AccountModuleScreenData.GotoModule(event.getData.asInstanceOf[Array[String]]))
            }
        })
        this.register(new ApplicationEventListener[ShellEvent.GotoFileModule]() {
            @Subscribe def handle(event: ShellEvent.GotoFileModule) {
                val fileModulePresenter: IFileModulePresenter = PresenterResolver.getPresenter(classOf[IFileModulePresenter])
                val screenData: FileModuleScreenData.GotoModule = new FileModuleScreenData.GotoModule(event.getData.asInstanceOf[Array[String]])
                fileModulePresenter.go(container, screenData)
            }
        })
    }
}
