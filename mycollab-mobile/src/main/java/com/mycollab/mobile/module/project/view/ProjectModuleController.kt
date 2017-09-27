package com.mycollab.mobile.module.project.view

import com.google.common.eventbus.Subscribe
import com.mycollab.common.ModuleNameConstants
import com.mycollab.common.domain.criteria.ActivityStreamSearchCriteria
import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum
import com.mycollab.core.MyCollabException
import com.mycollab.db.arguments.NumberSearchField
import com.mycollab.db.arguments.SearchField
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.db.arguments.StringSearchField
import com.mycollab.eventmanager.ApplicationEventListener
import com.mycollab.mobile.module.project.event.*
import com.mycollab.mobile.module.project.event.TicketEvent
import com.mycollab.mobile.module.project.view.message.MessagePresenter
import com.mycollab.mobile.module.project.view.milestone.MilestonePresenter
import com.mycollab.mobile.module.project.view.parameters.*
import com.mycollab.mobile.module.project.view.settings.ProjectUserPresenter
import com.mycollab.mobile.module.project.view.ticket.TicketPresenter
import com.mycollab.mobile.mvp.view.PresenterOptionUtil
import com.mycollab.module.project.CurrentProjectVariables
import com.mycollab.module.project.ProjectMemberStatusConstants
import com.mycollab.module.project.domain.*
import com.mycollab.module.project.domain.criteria.*
import com.mycollab.module.project.service.ProjectService
import com.mycollab.module.tracker.domain.SimpleBug
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.AppUI
import com.mycollab.vaadin.UserUIContext
import com.mycollab.vaadin.mvp.AbstractController
import com.mycollab.vaadin.mvp.PageActionChain
import com.mycollab.vaadin.mvp.PresenterResolver
import com.mycollab.vaadin.mvp.ScreenData
import com.vaadin.addon.touchkit.ui.NavigationManager

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class ProjectModuleController(val navManager: NavigationManager) : AbstractController() {
    init {
        bindProjectEvents()
        bindTicketEvents()
        bindBugEvents()
        bindMessageEvents()
        bindMilestoneEvents()
        bindTaskEvents()
        bindRiskEvents()
        bindMemberEvents()
    }

    private fun bindProjectEvents() {
        this.register(object : ApplicationEventListener<ProjectEvent.GotoAdd> {
            @Subscribe
            override fun handle(event: ProjectEvent.GotoAdd) {
                val presenter = PresenterOptionUtil.getPresenter(IProjectAddPresenter::class.java)
                presenter.go(navManager, ProjectScreenData.Add(SimpleProject()))
            }
        })
        this.register(object : ApplicationEventListener<ProjectEvent.GotoProjectList> {
            @Subscribe
            override fun handle(event: ProjectEvent.GotoProjectList) {
                val presenter = PresenterResolver.getPresenter(UserProjectListPresenter::class.java)
                val criteria = ProjectSearchCriteria()
                criteria.involvedMember = StringSearchField.and(UserUIContext.getUsername())
                criteria.projectStatuses = SetSearchField<String>(StatusI18nEnum.Open.name)
                presenter.go(navManager, ScreenData.Search<ProjectSearchCriteria>(criteria))
            }
        })
        this.register(object : ApplicationEventListener<ProjectEvent.GotoMyProject> {
            @Subscribe
            override fun handle(event: ProjectEvent.GotoMyProject) {
                val presenter = PresenterResolver.getPresenter(ProjectViewPresenter::class.java)
                presenter.handleChain(navManager, event.data as PageActionChain)
            }
        })
        this.register(object : ApplicationEventListener<ProjectEvent.GotoAllActivitiesView> {
            @Subscribe
            override fun handle(event: ProjectEvent.GotoAllActivitiesView) {
                val presenter = PresenterResolver.getPresenter(AllActivitiesStreamPresenter::class.java)
                val prjService = AppContextUtil.getSpringBean(ProjectService::class.java)
                val prjKeys = prjService.getProjectKeysUserInvolved(UserUIContext.getUsername(), AppUI.accountId)
                val searchCriteria = ActivityStreamSearchCriteria()
                searchCriteria.moduleSet = SetSearchField(ModuleNameConstants.PRJ)
                searchCriteria.saccountid = NumberSearchField(AppUI.accountId)
                searchCriteria.extraTypeIds = SetSearchField(prjKeys)
                presenter.go(navManager, ProjectScreenData.AllActivities(searchCriteria))
            }
        })
        this.register(object : ApplicationEventListener<ProjectEvent.MyProjectActivities> {
            @Subscribe
            override fun handle(event: ProjectEvent.MyProjectActivities) {
                val presenter = PresenterResolver.getPresenter(ProjectActivityStreamPresenter::class.java)
                val searchCriteria = ActivityStreamSearchCriteria()
                searchCriteria.moduleSet = SetSearchField(ModuleNameConstants.PRJ)
                searchCriteria.saccountid = NumberSearchField(AppUI.accountId)
                searchCriteria.extraTypeIds = SetSearchField(event.data as Int)
                presenter.go(navManager, ProjectScreenData.ProjectActivities(searchCriteria))
            }
        })
    }

    private fun bindTicketEvents() {
        this.register(object : ApplicationEventListener<TicketEvent.GotoDashboard> {
            @Subscribe
            override fun handle(event: TicketEvent.GotoDashboard) {
                val searchCriteria = ProjectTicketSearchCriteria()
                searchCriteria.projectIds = SetSearchField<Int>(CurrentProjectVariables.projectId)
                searchCriteria.types = CurrentProjectVariables.restrictedTicketTypes
                searchCriteria.isOpenned = SearchField()
                val data = TicketScreenData.GotoDashboard(searchCriteria)
                val presenter = PresenterResolver.getPresenter(TicketPresenter::class.java)
                presenter.go(navManager, data)
            }
        })
    }

    private fun bindBugEvents() {
        this.register(object : ApplicationEventListener<BugEvent.GotoRead> {
            @Subscribe
            override fun handle(event: BugEvent.GotoRead) {
                val data = BugScreenData.Read(event.data as Int)
                val presenter = PresenterResolver.getPresenter(TicketPresenter::class.java)
                presenter.go(navManager, data)
            }
        })
        this.register(object : ApplicationEventListener<BugEvent.GotoAdd> {
            @Subscribe
            override fun handle(event: BugEvent.GotoAdd) {
                val data = BugScreenData.Add(SimpleBug())
                val presenter = PresenterResolver.getPresenter(TicketPresenter::class.java)
                presenter.go(navManager, data)
            }
        })
        this.register(object : ApplicationEventListener<BugEvent.GotoEdit> {
            @Subscribe
            override fun handle(event: BugEvent.GotoEdit) {
                val data = BugScreenData.Edit(event.data as SimpleBug)
                val presenter = PresenterResolver.getPresenter(TicketPresenter::class.java)
                presenter.go(navManager, data)
            }
        })
    }

    private fun bindMessageEvents() {
        this.register(object : ApplicationEventListener<MessageEvent.GotoAdd> {
            @Subscribe
            override fun handle(event: MessageEvent.GotoAdd) {
                val presenter = PresenterResolver.getPresenter(MessagePresenter::class.java)
                presenter.go(navManager, MessageScreenData.Add())
            }
        })
        this.register(object : ApplicationEventListener<MessageEvent.GotoList> {
            @Subscribe
            override fun handle(event: MessageEvent.GotoList) {
                val searchCriteria = MessageSearchCriteria()
                searchCriteria.projectids = SetSearchField<Int>(CurrentProjectVariables.projectId)
                val data = MessageScreenData.Search(searchCriteria)
                val presenter = PresenterResolver.getPresenter(MessagePresenter::class.java)
                presenter.go(navManager, data)
            }
        })
        this.register(object : ApplicationEventListener<MessageEvent.GotoRead> {
            @Subscribe
            override fun handle(event: MessageEvent.GotoRead) {
                val presenter = PresenterResolver.getPresenter(MessagePresenter::class.java)
                presenter.go(navManager, MessageScreenData.Read(event.data as Int))
            }
        })
    }

    private fun bindMilestoneEvents() {
        this.register(object : ApplicationEventListener<MilestoneEvent.GotoList> {
            @Subscribe
            override fun handle(event: MilestoneEvent.GotoList) {
                val params = event.data
                val presenter = PresenterResolver.getPresenter(MilestonePresenter::class.java)
                if (params == null) {
                    val criteria = MilestoneSearchCriteria()
                    criteria.projectIds = SetSearchField<Int>(CurrentProjectVariables.projectId)
                    presenter.go(navManager, MilestoneScreenData.Search(criteria))
                } else if (params is MilestoneScreenData.Search) {
                    presenter.go(navManager, params)
                } else {
                    throw MyCollabException("Invalid search parameter $params")
                }
            }
        })
        this.register(object : ApplicationEventListener<MilestoneEvent.GotoRead> {
            @Subscribe
            override fun handle(event: MilestoneEvent.GotoRead) {
                val data = MilestoneScreenData.Read(event.data as Int)
                val presenter = PresenterResolver.getPresenter(MilestonePresenter::class.java)
                presenter.go(navManager, data)
            }
        })
        this.register(object : ApplicationEventListener<MilestoneEvent.GotoAdd> {
            @Subscribe
            override fun handle(event: MilestoneEvent.GotoAdd) {
                val data = MilestoneScreenData.Add(SimpleMilestone())
                val presenter = PresenterResolver.getPresenter(MilestonePresenter::class.java)
                presenter.go(navManager, data)
            }
        })
        this.register(object : ApplicationEventListener<MilestoneEvent.GotoEdit> {
            @Subscribe
            override fun handle(event: MilestoneEvent.GotoEdit) {
                val data = MilestoneScreenData.Edit(event.data as SimpleMilestone)
                val presenter = PresenterResolver.getPresenter(MilestonePresenter::class.java)
                presenter.go(navManager, data)
            }
        })
    }

    private fun bindTaskEvents() {
        this.register(object : ApplicationEventListener<TaskEvent.GotoRead> {
            @Subscribe
            override fun handle(event: TaskEvent.GotoRead) {
                val data = TaskScreenData.Read(event.data as Int)
                val presenter = PresenterResolver.getPresenter(TicketPresenter::class.java)
                presenter.go(navManager, data)
            }
        })
        this.register(object : ApplicationEventListener<TaskEvent.GotoEdit> {
            @Subscribe
            override fun handle(event: TaskEvent.GotoEdit) {
                val data = TaskScreenData.Edit(event.data as SimpleTask)
                val presenter = PresenterResolver.getPresenter(TicketPresenter::class.java)
                presenter.go(navManager, data)
            }
        })
        this.register(object : ApplicationEventListener<TaskEvent.GotoAdd> {
            @Subscribe
            override fun handle(event: TaskEvent.GotoAdd) {
                val data = TaskScreenData.Add(SimpleTask())
                val presenter = PresenterResolver.getPresenter(TicketPresenter::class.java)
                presenter.go(navManager, data)
            }
        })
    }

    private fun bindRiskEvents() {
        this.register(object : ApplicationEventListener<RiskEvent.GotoRead> {
            @Subscribe
            override fun handle(event: RiskEvent.GotoRead) {
                val data = RiskScreenData.Read(event.data as Int)
                val presenter = PresenterOptionUtil.getPresenter(TicketPresenter::class.java)
                presenter.go(navManager, data)
            }
        })
        this.register(object : ApplicationEventListener<RiskEvent.GotoAdd> {
            @Subscribe
            override fun handle(event: RiskEvent.GotoAdd) {
                val data = RiskScreenData.Add(SimpleRisk())
                val presenter = PresenterOptionUtil.getPresenter(TicketPresenter::class.java)
                presenter.go(navManager, data)
            }
        })
        this.register(object : ApplicationEventListener<RiskEvent.GotoEdit> {
            @Subscribe
            override fun handle(event: RiskEvent.GotoEdit) {
                val data = RiskScreenData.Edit(event.data as SimpleRisk)
                val presenter = PresenterOptionUtil.getPresenter(TicketPresenter::class.java)
                presenter.go(navManager, data)
            }
        })
    }

    private fun bindMemberEvents() {
        this.register(object : ApplicationEventListener<ProjectMemberEvent.GotoList> {
            @Subscribe
            override fun handle(event: ProjectMemberEvent.GotoList) {
                val criteria = ProjectMemberSearchCriteria()
                criteria.projectId = NumberSearchField.equal(CurrentProjectVariables.projectId)
                criteria.saccountid = NumberSearchField.equal(AppUI.accountId)
                criteria.statuses = SetSearchField(ProjectMemberStatusConstants.ACTIVE, ProjectMemberStatusConstants.NOT_ACCESS_YET)
                val presenter = PresenterResolver.getPresenter(ProjectUserPresenter::class.java)
                presenter.go(navManager, ProjectMemberScreenData.Search(criteria))
            }
        })
        this.register(object : ApplicationEventListener<ProjectMemberEvent.GotoRead> {
            @Subscribe
            override fun handle(event: ProjectMemberEvent.GotoRead) {
                val data = ProjectMemberScreenData.Read(event.data)
                val presenter = PresenterResolver.getPresenter(ProjectUserPresenter::class.java)
                presenter.go(navManager, data)
            }
        })
        this.register(object : ApplicationEventListener<ProjectMemberEvent.GotoEdit> {
            @Subscribe
            override fun handle(event: ProjectMemberEvent.GotoEdit) {
                val data = ProjectMemberScreenData.Edit(event.data as SimpleProjectMember)
                val presenter = PresenterResolver.getPresenter(ProjectUserPresenter::class.java)
                presenter.go(navManager, data)
            }
        })
        this.register(object : ApplicationEventListener<ProjectMemberEvent.GotoInviteMembers> {
            @Subscribe
            override fun handle(event: ProjectMemberEvent.GotoInviteMembers) {
                val data = ProjectMemberScreenData.InviteProjectMembers()
                val presenter = PresenterResolver.getPresenter(ProjectUserPresenter::class.java)
                presenter.go(navManager, data)
            }
        })
    }
}