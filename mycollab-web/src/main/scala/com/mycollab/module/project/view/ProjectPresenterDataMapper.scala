package com.mycollab.module.project.view

import com.mycollab.module.project.view.bug.BugPresenter
import com.mycollab.module.project.view.file.FilePresenter
import com.mycollab.module.project.view.message.MessagePresenter
import com.mycollab.module.project.view.milestone.MilestonePresenter
import com.mycollab.module.project.view.page.PagePresenter
import com.mycollab.module.project.view.parameters._
import com.mycollab.module.project.view.risk.IRiskPresenter
import com.mycollab.module.project.view.settings.UserSettingPresenter
import com.mycollab.module.project.view.ticket.TicketPresenter
import com.mycollab.module.project.view.time.IFinancePresenter
import com.mycollab.module.project.view.user.ProjectDashboardPresenter
import com.mycollab.vaadin.mvp.{IPresenter, ScreenData}

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object ProjectPresenterDataMapper {
  val milestoneMapper = Map[Class[_ <: ScreenData[_]], Class[_ <: IPresenter[_]]](
    classOf[MilestoneScreenData.Read] -> classOf[MilestonePresenter],
    classOf[MilestoneScreenData.Search] -> classOf[MilestonePresenter],
    classOf[MilestoneScreenData.Add] -> classOf[MilestonePresenter],
    classOf[MilestoneScreenData.Edit] -> classOf[MilestonePresenter],
    classOf[MilestoneScreenData.Kanban] -> classOf[MilestonePresenter],
    classOf[MilestoneScreenData.Roadmap] -> classOf[MilestonePresenter])

  val messageMapper = Map[Class[_ <: ScreenData[_]], Class[_ <: IPresenter[_]]](
    classOf[MessageScreenData.Read] -> classOf[MessagePresenter],
    classOf[MessageScreenData.Search] -> classOf[MessagePresenter])

  val pageMapper = Map[Class[_ <: ScreenData[_]], Class[_ <: IPresenter[_]]](
    classOf[PageScreenData.Read] -> classOf[PagePresenter],
    classOf[PageScreenData.Add] -> classOf[PagePresenter],
    classOf[PageScreenData.Edit] -> classOf[PagePresenter],
    classOf[PageScreenData.Search] -> classOf[PagePresenter])

  val riskMapper = Map[Class[_ <: ScreenData[_]], Class[_ <: IPresenter[_]]](
    classOf[RiskScreenData.Add] -> classOf[IRiskPresenter],
    classOf[RiskScreenData.Edit] -> classOf[IRiskPresenter],
    classOf[RiskScreenData.Read] -> classOf[IRiskPresenter])

  val taskMapper = Map[Class[_ <: ScreenData[_]], Class[_ <: IPresenter[_]]](
    classOf[TaskScreenData.Add] -> classOf[TicketPresenter],
    classOf[TaskScreenData.Edit] -> classOf[TicketPresenter],
    classOf[TaskScreenData.Read] -> classOf[TicketPresenter],
    classOf[TicketScreenData.GotoDashboard] -> classOf[TicketPresenter],
    classOf[TaskScreenData.GotoKanbanView] -> classOf[TicketPresenter])

  val trackerMapper = Map[Class[_ <: ScreenData[_]], Class[_ <: IPresenter[_]]](
    classOf[BugScreenData.Read] -> classOf[BugPresenter],
    classOf[BugScreenData.Add] -> classOf[BugPresenter],
    classOf[BugScreenData.Edit] -> classOf[BugPresenter])

  val standupMapper = Map[Class[_ <: ScreenData[_]], Class[_ <: IPresenter[_]]](
    classOf[StandupScreenData.Search] -> classOf[ProjectDashboardPresenter])

  val userMapper = Map[Class[_ <: ScreenData[_]], Class[_ <: IPresenter[_]]](
    classOf[ProjectMemberScreenData.Add] -> classOf[UserSettingPresenter],
    classOf[ProjectMemberScreenData.InviteProjectMembers] -> classOf[UserSettingPresenter],
    classOf[ProjectMemberScreenData.Read] -> classOf[UserSettingPresenter],
    classOf[ProjectMemberScreenData.Search] -> classOf[UserSettingPresenter],
    classOf[ProjectRoleScreenData.Add] -> classOf[UserSettingPresenter],
    classOf[ProjectRoleScreenData.Read] -> classOf[UserSettingPresenter],
    classOf[ProjectRoleScreenData.Search] -> classOf[UserSettingPresenter],
    classOf[ProjectSettingScreenData.ViewSettings] -> classOf[UserSettingPresenter],
    classOf[ComponentScreenData.Add] -> classOf[UserSettingPresenter],
    classOf[ComponentScreenData.Edit] -> classOf[UserSettingPresenter],
    classOf[ComponentScreenData.Read] -> classOf[UserSettingPresenter],
    classOf[ComponentScreenData.Search] -> classOf[UserSettingPresenter],
    classOf[VersionScreenData.Add] -> classOf[UserSettingPresenter],
    classOf[VersionScreenData.Edit] -> classOf[UserSettingPresenter],
    classOf[VersionScreenData.Read] -> classOf[UserSettingPresenter],
    classOf[VersionScreenData.Search] -> classOf[UserSettingPresenter])

  val timeMapper = Map[Class[_ <: ScreenData[_]], Class[_ <: IPresenter[_]]](
    classOf[TimeTrackingScreenData.Search] -> classOf[IFinancePresenter],
    classOf[InvoiceScreenData.GotoInvoiceList] -> classOf[IFinancePresenter])

  val fileMapper = Map[Class[_ <: ScreenData[_]], Class[_ <: IPresenter[_]]](
    classOf[FileScreenData.GotoDashboard] -> classOf[FilePresenter])

  val projectMapper = Map[Class[_ <: ScreenData[_]], Class[_ <: IPresenter[_]]](
    classOf[ProjectScreenData.GotoTagList] -> classOf[ProjectDashboardPresenter],
    classOf[ProjectScreenData.GotoFavorite] -> classOf[ProjectDashboardPresenter],
    classOf[ProjectScreenData.GotoGanttChart] -> classOf[ProjectDashboardPresenter],
    classOf[ProjectScreenData.GotoCalendarView] -> classOf[ProjectDashboardPresenter],
    classOf[ProjectScreenData.GotoReportConsole] -> classOf[ProjectDashboardPresenter],
    classOf[ProjectScreenData.SearchItem] -> classOf[UserProjectDashboardPresenter],
    classOf[ProjectScreenData.Edit] -> classOf[ProjectDashboardPresenter],
    classOf[ReportScreenData.GotoWeeklyTiming] -> classOf[ProjectDashboardPresenter])

  val allMapper = milestoneMapper ++ messageMapper ++ pageMapper  ++ riskMapper ++ taskMapper ++
    trackerMapper ++ standupMapper ++ userMapper ++ timeMapper ++ fileMapper ++ projectMapper

  def presenter(screenData: ScreenData[_]): Class[_ <: IPresenter[_]] = {
    val _value = allMapper get screenData.getClass
    if (_value.isInstanceOf[Some[_]]) _value.get else null
  }
}
