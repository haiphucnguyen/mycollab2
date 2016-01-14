package com.esofthead.mycollab.mobile.module.project.view

import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum
import com.esofthead.mycollab.core.MyCollabException
import com.esofthead.mycollab.core.arguments.{NumberSearchField, SearchField, SetSearchField, StringSearchField}
import com.esofthead.mycollab.core.utils.BeanUtility
import com.esofthead.mycollab.eventmanager.ApplicationEventListener
import com.esofthead.mycollab.mobile.module.project.events._
import com.esofthead.mycollab.mobile.module.project.view.bug.BugPresenter
import com.esofthead.mycollab.mobile.module.project.view.message.MessagePresenter
import com.esofthead.mycollab.mobile.module.project.view.milestone.MilestonePresenter
import com.esofthead.mycollab.mobile.module.project.view.parameters.ProjectScreenData.{Add, ViewActivities}
import com.esofthead.mycollab.mobile.module.project.view.parameters._
import com.esofthead.mycollab.mobile.module.project.view.settings.ProjectUserPresenter
import com.esofthead.mycollab.mobile.module.project.view.task.TaskPresenter
import com.esofthead.mycollab.module.project.domain.criteria._
import com.esofthead.mycollab.module.project.domain.{SimpleMilestone, SimpleProject, SimpleProjectMember, SimpleTask}
import com.esofthead.mycollab.module.project.{CurrentProjectVariables, ProjectMemberStatusConstants}
import com.esofthead.mycollab.module.tracker.domain.SimpleBug
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria
import com.esofthead.mycollab.vaadin.AppContext
import com.esofthead.mycollab.vaadin.mvp.{AbstractController, PageActionChain, PresenterResolver, ScreenData}
import com.google.common.eventbus.Subscribe
import com.vaadin.addon.touchkit.ui.NavigationManager

/**
  * @author MyCollab Ltd
  * @since 5.2.5
  */
class ProjectModuleController(val navManager: NavigationManager) extends AbstractController {
  bindProjectEvents()
  bindBugEvents()
  bindMessageEvents()
  bindMilestoneEvents()
  bindTaskEvents()
  bindMemberEvents()

  private def bindProjectEvents() {
    this.register(new ApplicationEventListener[ProjectEvent.GotoAdd]() {
      @Subscribe def handle(event: ProjectEvent.GotoAdd): Unit = {
        val presenter = PresenterResolver.getPresenter(classOf[ProjectAddPresenter])
        presenter.go(navManager, new Add(new SimpleProject))
      }
    })
    this.register(new ApplicationEventListener[ProjectEvent.GotoProjectList]() {
      @Subscribe def handle(event: ProjectEvent.GotoProjectList) {
        val presenter = PresenterResolver.getPresenter(classOf[ProjectListPresenter])
        val criteria = new ProjectSearchCriteria
        criteria.setInvolvedMember(new StringSearchField(AppContext.getUsername))
        criteria.setProjectStatuses(new SetSearchField[String](StatusI18nEnum.Open.name))
        presenter.go(navManager, new ScreenData.Search[ProjectSearchCriteria](criteria))
      }
    })
    this.register(new ApplicationEventListener[ProjectEvent.GotoMyProject]() {
      @Subscribe def handle(event: ProjectEvent.GotoMyProject) {
        val presenter = PresenterResolver.getPresenter(classOf[ProjectViewPresenter])
        presenter.handleChain(navManager, event.getData.asInstanceOf[PageActionChain])
      }
    })
    this.register(new ApplicationEventListener[ProjectEvent.AllActivities]() {
      @Subscribe def handle(event: ProjectEvent.AllActivities) {
        val presenter = PresenterResolver.getPresenter(classOf[AllActivityStreamPresenter])
        presenter.go(navManager, event.getData.asInstanceOf[ProjectScreenData.AllActivities])
      }
    })
    this.register(new ApplicationEventListener[ProjectEvent.MyProjectActivities]() {
      @Subscribe def handle(event: ProjectEvent.MyProjectActivities) {
        val presenter: ProjectActivityStreamPresenter = PresenterResolver.getPresenter(classOf[ProjectActivityStreamPresenter])
        presenter.go(navManager, new ViewActivities(event.getData.asInstanceOf[Integer]))
      }
    })
  }

  private def bindBugEvents() {
    this.register(new ApplicationEventListener[BugEvent.GotoList]() {
      @Subscribe def handle(event: BugEvent.GotoList) {
        val params: Any = event.getData
        val presenter = PresenterResolver.getPresenter(classOf[BugPresenter])
        if (params == null) {
          val criteria = new BugSearchCriteria
          criteria.setProjectId(new NumberSearchField(SearchField.AND, CurrentProjectVariables.getProjectId))
          presenter.go(navManager, new BugScreenData.Search(criteria))
        }
        else if (params.isInstanceOf[BugScreenData.Search]) {
          presenter.go(navManager, params.asInstanceOf[BugScreenData.Search])
        }
        else {
          throw new MyCollabException("Invalid search parameter: " + BeanUtility.printBeanObj(params))
        }
      }
    })
    this.register(new ApplicationEventListener[BugEvent.GotoRead]() {
      @Subscribe def handle(event: BugEvent.GotoRead) {
        val data = new BugScreenData.Read(event.getData.asInstanceOf[Integer])
        val presenter = PresenterResolver.getPresenter(classOf[BugPresenter])
        presenter.go(navManager, data)
      }
    })
    this.register(new ApplicationEventListener[BugEvent.GotoAdd]() {
      @Subscribe def handle(event: BugEvent.GotoAdd) {
        val data = new BugScreenData.Add(new SimpleBug)
        val presenter = PresenterResolver.getPresenter(classOf[BugPresenter])
        presenter.go(navManager, data)
      }
    })
    this.register(new ApplicationEventListener[BugEvent.GotoEdit]() {
      @Subscribe def handle(event: BugEvent.GotoEdit) {
        val data: BugScreenData.Edit = new BugScreenData.Edit(event.getData.asInstanceOf[SimpleBug])
        val presenter: BugPresenter = PresenterResolver.getPresenter(classOf[BugPresenter])
        presenter.go(navManager, data)
      }
    })
  }

  private def bindMessageEvents() {
    this.register(new ApplicationEventListener[MessageEvent.GotoAdd]() {
      @Subscribe def handle(event: MessageEvent.GotoAdd) {
        val data = new MessageScreenData.Add
        val presenter = PresenterResolver.getPresenter(classOf[MessagePresenter])
        presenter.go(navManager, data)
      }
    })
    this.register(new ApplicationEventListener[MessageEvent.GotoList]() {
      @Subscribe def handle(event: MessageEvent.GotoList) {
        val searchCriteria = new MessageSearchCriteria
        searchCriteria.setProjectids(new SetSearchField[Integer](CurrentProjectVariables.getProjectId))
        val data = new MessageScreenData.Search(searchCriteria)
        val presenter = PresenterResolver.getPresenter(classOf[MessagePresenter])
        presenter.go(navManager, data)
      }
    })
    this.register(new ApplicationEventListener[MessageEvent.GotoRead]() {
      @Subscribe def handle(event: MessageEvent.GotoRead) {
        val data = new MessageScreenData.Read(event.getData.asInstanceOf[Integer])
        val presenter = PresenterResolver.getPresenter(classOf[MessagePresenter])
        presenter.go(navManager, data)
      }
    })
  }

  private def bindMilestoneEvents() {
    this.register(new ApplicationEventListener[MilestoneEvent.GotoList]() {
      @Subscribe def handle(event: MilestoneEvent.GotoList) {
        val params: Any = event.getData
        val presenter: MilestonePresenter = PresenterResolver.getPresenter(classOf[MilestonePresenter])
        if (params == null) {
          val criteria = new MilestoneSearchCriteria
          criteria.setProjectId(new NumberSearchField(SearchField.AND, CurrentProjectVariables.getProjectId))
          presenter.go(navManager, new MilestoneScreenData.Search(criteria))
        }
        else if (params.isInstanceOf[MilestoneScreenData.Search]) {
          presenter.go(navManager, params.asInstanceOf[MilestoneScreenData.Search])
        }
        else {
          throw new MyCollabException("Invalid search parameter: " + BeanUtility.printBeanObj(params))
        }
      }
    })
    this.register(new ApplicationEventListener[MilestoneEvent.GotoRead]() {
      @Subscribe def handle(event: MilestoneEvent.GotoRead) {
        val data = new MilestoneScreenData.Read(event.getData.asInstanceOf[Integer])
        val presenter = PresenterResolver.getPresenter(classOf[MilestonePresenter])
        presenter.go(navManager, data)
      }
    })
    this.register(new ApplicationEventListener[MilestoneEvent.GotoAdd]() {
      @Subscribe def handle(event: MilestoneEvent.GotoAdd) {
        val data = new MilestoneScreenData.Add(new SimpleMilestone)
        val presenter = PresenterResolver.getPresenter(classOf[MilestonePresenter])
        presenter.go(navManager, data)
      }
    })
    this.register(new ApplicationEventListener[MilestoneEvent.GotoEdit]() {
      @Subscribe def handle(event: MilestoneEvent.GotoEdit) {
        val data = new MilestoneScreenData.Edit(event.getData.asInstanceOf[SimpleMilestone])
        val presenter = PresenterResolver.getPresenter(classOf[MilestonePresenter])
        presenter.go(navManager, data)
      }
    })
  }

  private def bindTaskEvents() {
    this.register(new ApplicationEventListener[TaskEvent.GotoList]() {
      @Subscribe def handle(event: TaskEvent.GotoList) {
        val criteria = new TaskSearchCriteria
        criteria.setProjectid(new NumberSearchField(SearchField.AND, CurrentProjectVariables.getProjectId))
        val data = new TaskScreenData.Search(criteria)
        val presenter = PresenterResolver.getPresenter(classOf[TaskPresenter])
        presenter.go(navManager, data)
      }
    })
    this.register(new ApplicationEventListener[TaskEvent.GotoRead]() {
      @Subscribe def handle(event: TaskEvent.GotoRead) {
        val data = new TaskScreenData.Read(event.getData.asInstanceOf[Integer])
        val presenter = PresenterResolver.getPresenter(classOf[TaskPresenter])
        presenter.go(navManager, data)
      }
    })
    this.register(new ApplicationEventListener[TaskEvent.GotoEdit]() {
      @Subscribe def handle(event: TaskEvent.GotoEdit) {
        val data = new TaskScreenData.Edit(event.getData.asInstanceOf[SimpleTask])
        val presenter = PresenterResolver.getPresenter(classOf[TaskPresenter])
        presenter.go(navManager, data)
      }
    })
    this.register(new ApplicationEventListener[TaskEvent.GotoAdd]() {
      @Subscribe def handle(event: TaskEvent.GotoAdd) {
        val data = new TaskScreenData.Add(new SimpleTask)
        val presenter = PresenterResolver.getPresenter(classOf[TaskPresenter])
        presenter.go(navManager, data)
      }
    })
  }

  private def bindMemberEvents() {
    this.register(new ApplicationEventListener[ProjectMemberEvent.GotoList]() {
      @Subscribe def handle(event: ProjectMemberEvent.GotoList) {
        val criteria = new ProjectMemberSearchCriteria
        criteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId))
        criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId))
        criteria.setStatus(new StringSearchField(ProjectMemberStatusConstants.ACTIVE))
        val presenter = PresenterResolver.getPresenter(classOf[ProjectUserPresenter])
        presenter.go(navManager, new ProjectMemberScreenData.Search(criteria))
      }
    })
    this.register(new ApplicationEventListener[ProjectMemberEvent.GotoRead]() {
      @Subscribe def handle(event: ProjectMemberEvent.GotoRead) {
        val data = new ProjectMemberScreenData.Read(event.getData)
        val presenter = PresenterResolver.getPresenter(classOf[ProjectUserPresenter])
        presenter.go(navManager, data)
      }
    })
    this.register(new ApplicationEventListener[ProjectMemberEvent.GotoEdit]() {
      @Subscribe def handle(event: ProjectMemberEvent.GotoEdit) {
        val data = new ProjectMemberScreenData.Edit(event.getData.asInstanceOf[SimpleProjectMember])
        val presenter = PresenterResolver.getPresenter(classOf[ProjectUserPresenter])
        presenter.go(navManager, data)
      }
    })
    this.register(new ApplicationEventListener[ProjectMemberEvent.GotoInviteMembers]() {
      @Subscribe def handle(event: ProjectMemberEvent.GotoInviteMembers) {
        val data = new ProjectMemberScreenData.InviteProjectMembers
        val presenter = PresenterResolver.getPresenter(classOf[ProjectUserPresenter])
        presenter.go(navManager, data)
      }
    })
  }
}
