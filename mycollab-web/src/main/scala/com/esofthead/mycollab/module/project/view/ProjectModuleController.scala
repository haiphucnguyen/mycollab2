package com.esofthead.mycollab.module.project.view

import com.esofthead.mycollab.eventmanager.ApplicationEventListener
import com.esofthead.mycollab.module.crm.domain.SimpleAccount
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria
import com.esofthead.mycollab.module.project.events.ProjectEvent.GotoMyProject
import com.esofthead.mycollab.module.project.events.{ClientEvent, ProjectEvent}
import com.esofthead.mycollab.module.project.view.client.IClientPresenter
import com.esofthead.mycollab.module.project.view.parameters.ClientScreenData
import com.esofthead.mycollab.module.project.view.parameters.ClientScreenData.{Add, Read}
import com.esofthead.mycollab.vaadin.mvp.{AbstractController, PageActionChain, PresenterResolver}
import com.google.common.eventbus.Subscribe

/**
  * @author MyCollab Ltd.
  * @since 5.0.9
  */
class ProjectModuleController(val container: ProjectModule) extends AbstractController {
  this.register(new ApplicationEventListener[ProjectEvent.GotoMyProject]() {
    @Subscribe override def handle(event: GotoMyProject): Unit = {
      val presenter = PresenterResolver.getPresenter(classOf[ProjectViewPresenter])
      presenter.handleChain(container, event.getData.asInstanceOf[PageActionChain])
    }
  })

  this.register(new ApplicationEventListener[ClientEvent.GotoList]() {
    @Subscribe override def handle(event: ClientEvent.GotoList): Unit = {
      val presenter = PresenterResolver.getPresenter(classOf[IClientPresenter])
      val searchCriteria = new AccountSearchCriteria
      presenter.go(container, new ClientScreenData.Search(searchCriteria))
    }
  })

  this.register(new ApplicationEventListener[ClientEvent.GotoAdd]() {
    @Subscribe def handle(event: ClientEvent.GotoAdd): Unit = {
      val presenter = PresenterResolver.getPresenter(classOf[IClientPresenter])
      val account = new SimpleAccount
      presenter.go(container, new Add(account))
    }
  })

  this.register(new ApplicationEventListener[ClientEvent.GotoRead]() {
    @Subscribe def handle(event: ClientEvent.GotoRead): Unit = {
      val presenter = PresenterResolver.getPresenter(classOf[IClientPresenter])
      val clientId = event.getData.asInstanceOf[Integer]
      presenter.go(container, new Read(clientId))
    }
  })
}
