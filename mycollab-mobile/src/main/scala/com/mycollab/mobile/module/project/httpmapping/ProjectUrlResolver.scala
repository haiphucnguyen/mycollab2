package com.mycollab.mobile.module.project.httpmapping

import com.mycollab.common.domain.criteria.ActivityStreamSearchCriteria
import com.mycollab.common.{ModuleNameConstants, UrlTokenizer}
import com.mycollab.db.arguments.{NumberSearchField, SetSearchField}
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.mobile.module.project.events.ProjectEvent
import com.mycollab.mobile.module.project.view.parameters.ProjectScreenData
import com.mycollab.mobile.module.project.view.parameters.ProjectScreenData.AllActivities
import com.mycollab.mobile.shell.ModuleHelper
import com.mycollab.mobile.shell.events.ShellEvent
import com.mycollab.module.project.service.ProjectService
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.mvp.{PageActionChain, UrlResolver}
import com.mycollab.vaadin.{AppUI, UserUIContext}

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class ProjectUrlResolver extends UrlResolver {
  def build: UrlResolver = {
    this.addSubResolver("dashboard", new DashboardUrlResolver)
    this.addSubResolver("activities", new ActivityUrlResolver)
    this.addSubResolver("message", new MessageUrlResolver)
    this.addSubResolver("milestone", new MilestoneUrlResolver)
    this.addSubResolver("ticket", new TicketUrlResolver)
    this.addSubResolver("task", new TaskUrlResolver)
    this.addSubResolver("bug", new BugUrlResolver)
    this.addSubResolver("risk", new RiskUrlResolver)
    this.addSubResolver("user", new UserUrlResolver)
    this
  }

  override def handle(params: String*) {
    if (!ModuleHelper.isCurrentProjectModule) {
      EventBusFactory.getInstance().post(new ShellEvent.GotoProjectModule(this, params))
    }
    else {
      super.handle(params: _*)
    }
  }

  protected def defaultPageErrorHandler() {
    EventBusFactory.getInstance().post(new ShellEvent.GotoProjectModule(this, null))
  }

  protected override def handlePage(params: String*) {
    super.handlePage(params: _*)
    EventBusFactory.getInstance().post(new ProjectEvent.GotoProjectList(this, null))
  }

  class DashboardUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      if (params.isEmpty) {
        EventBusFactory.getInstance().post(new ShellEvent.GotoProjectModule(this, null))
      }
      else {
        val projectId = UrlTokenizer(params(0)).getInt
        val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new ProjectScreenData.GotoDashboard)
        EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
      }
    }
  }

  class ActivityUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      if (params.isEmpty) {
        val prjService = AppContextUtil.getSpringBean(classOf[ProjectService])
        val prjKeys = prjService.getProjectKeysUserInvolved(UserUIContext.getUsername, AppUI.getAccountId)
        val searchCriteria = new ActivityStreamSearchCriteria()
        searchCriteria.setModuleSet(new SetSearchField(ModuleNameConstants.PRJ))
        searchCriteria.setSaccountid(new NumberSearchField(AppUI.getAccountId))
        searchCriteria.setExtraTypeIds(new SetSearchField(prjKeys))

        val data = new AllActivities(searchCriteria)
        EventBusFactory.getInstance().post(new ProjectEvent.GotoAllActivitiesView(this, data))
      }
      else {
        val projectId =  UrlTokenizer(params(0)).getInt
        val searchCriteria = new ActivityStreamSearchCriteria()
        searchCriteria.setModuleSet(new SetSearchField(ModuleNameConstants.PRJ))
        searchCriteria.setSaccountid(new NumberSearchField(AppUI.getAccountId))
        searchCriteria.setExtraTypeIds(new SetSearchField(projectId))
        val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new ProjectScreenData.ProjectActivities(searchCriteria))
        EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
      }
    }
  }
}