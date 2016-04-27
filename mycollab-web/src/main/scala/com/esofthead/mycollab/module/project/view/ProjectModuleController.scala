package com.esofthead.mycollab.module.project.view

import java.util.GregorianCalendar

import com.esofthead.mycollab.eventmanager.ApplicationEventListener
import com.esofthead.mycollab.module.crm.domain.SimpleAccount
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria
import com.esofthead.mycollab.module.project.CurrentProjectVariables
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport
import com.esofthead.mycollab.module.project.events.ProjectEvent.GotoMyProject
import com.esofthead.mycollab.module.project.events.{ClientEvent, ProjectEvent, ReportEvent, StandUpEvent}
import com.esofthead.mycollab.module.project.service.StandupReportService
import com.esofthead.mycollab.module.project.view.client.IClientPresenter
import com.esofthead.mycollab.module.project.view.parameters.ClientScreenData.{Add, Read}
import com.esofthead.mycollab.module.project.view.parameters.{ClientScreenData, ProjectScreenData, StandupScreenData}
import com.esofthead.mycollab.module.project.view.reports.IReportPresenter
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.esofthead.mycollab.vaadin.AppContext
import com.esofthead.mycollab.vaadin.mvp.{AbstractController, PageActionChain, PresenterResolver, ScreenData}
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

  this.register(new ApplicationEventListener[ProjectEvent.GotoList]() {
    @Subscribe override def handle(event: ProjectEvent.GotoList): Unit = {
      val presenter = PresenterResolver.getPresenter(classOf[UserDashboardPresenter])
      presenter.go(container, new ProjectScreenData.GotoList())
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

  this.register(new ApplicationEventListener[ClientEvent.GotoEdit]() {
    @Subscribe def handle(event: ClientEvent.GotoEdit): Unit = {
      val presenter = PresenterResolver.getPresenter(classOf[IClientPresenter])
      presenter.go(container, new ScreenData.Edit[Any](event.getData))
    }
  })

  this.register(new ApplicationEventListener[ClientEvent.GotoRead]() {
    @Subscribe def handle(event: ClientEvent.GotoRead): Unit = {
      val presenter = PresenterResolver.getPresenter(classOf[IClientPresenter])
      val clientId = event.getData.asInstanceOf[Integer]
      presenter.go(container, new Read(clientId))
    }
  })

  this.register(new ApplicationEventListener[ReportEvent.GotoConsole]() {
    @Subscribe override def handle(event: ReportEvent.GotoConsole): Unit = {
      val presenter = PresenterResolver.getPresenter(classOf[IReportPresenter])
      presenter.go(container, null)
    }
  })

  this.register(new ApplicationEventListener[StandUpEvent.GotoAdd] {
    @Subscribe def handle(event: StandUpEvent.GotoAdd) {
      val standupService = ApplicationContextUtil.getSpringBean(classOf[StandupReportService])
      var standupReport = standupService.findStandupReportByDateUser(CurrentProjectVariables.getProjectId,
        AppContext.getUsername, new GregorianCalendar().getTime, AppContext.getAccountId)
      if (standupReport == null) {
        standupReport = new SimpleStandupReport
      }
      val data = new StandupScreenData.Add(standupReport)
      val presenter = PresenterResolver.getPresenter(classOf[IReportPresenter])
      presenter.go(container, data)
    }
  })
  this.register(new ApplicationEventListener[StandUpEvent.GotoList] {
    @Subscribe def handle(event: StandUpEvent.GotoList) {
      val presenter = PresenterResolver.getPresenter(classOf[IReportPresenter])
      presenter.go(container, new StandupScreenData.Search(new GregorianCalendar().getTime))
    }
  })
}
