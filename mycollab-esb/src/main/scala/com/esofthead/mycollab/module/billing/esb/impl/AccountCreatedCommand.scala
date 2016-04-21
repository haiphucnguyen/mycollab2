package com.esofthead.mycollab.module.billing.esb.impl

import java.util
import java.util.GregorianCalendar

import com.esofthead.mycollab.common.NotificationType
import com.esofthead.mycollab.common.domain.OptionVal
import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum
import com.esofthead.mycollab.common.i18n.WikiI18nEnum
import com.esofthead.mycollab.common.service.OptionValService
import com.esofthead.mycollab.core.utils.{BeanUtility, StringUtils}
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.billing.esb.AccountCreatedEvent
import com.esofthead.mycollab.module.ecm.service.ResourceService
import com.esofthead.mycollab.module.file.PathUtils
import com.esofthead.mycollab.module.page.domain.{Folder, Page}
import com.esofthead.mycollab.module.page.service.PageService
import com.esofthead.mycollab.module.project.ProjectTypeConstants
import com.esofthead.mycollab.module.project.domain._
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum._
import com.esofthead.mycollab.module.project.service._
import com.esofthead.mycollab.module.tracker.domain.{BugWithBLOBs, Version}
import com.esofthead.mycollab.module.tracker.service._
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.1.1
  */
@Component class AccountCreatedCommand extends GenericCommand {

  @Autowired private val optionValService: OptionValService = null
  @Autowired private val projectService: ProjectService = null
  @Autowired private val messageService: MessageService = null
  @Autowired private val milestoneService: MilestoneService = null
  @Autowired private val taskService: ProjectTaskService = null
  @Autowired private val bugService: BugService = null
  @Autowired private val bugRelatedService: BugRelatedItemService = null
  @Autowired private val componentService: ComponentService = null
  @Autowired private val versionService: VersionService = null
  @Autowired private val pageService: PageService = null
  @Autowired private val resourceService: ResourceService = null
  @Autowired val projectNotificationSettingService: ProjectNotificationSettingService = null

  @AllowConcurrentEvents
  @Subscribe
  def execute(event: AccountCreatedEvent): Unit = {
    createDefaultOptionVals(event.accountId)
    if (event.createSampleData) {
      createSampleProjectData(event.initialUser, event.accountId)
    }
  }

  private def createDefaultOptionVals(accountId: Integer): Unit = {
    val option = new OptionVal
    option.setCreatedtime(new GregorianCalendar().getTime)
    option.setIsdefault(true)
    option.setSaccountid(accountId)
    option.setType(ProjectTypeConstants.TASK)
    option.setTypeval(StatusI18nEnum.Open.name())
    option.setColor("fdde86")
    option.setFieldgroup("status")
    optionValService.saveWithSession(option, null)

    option.setTypeval(StatusI18nEnum.InProgress.name())
    option.setId(null)
    optionValService.saveWithSession(option, null)

    option.setTypeval(StatusI18nEnum.Archived.name())
    option.setId(null)
    optionValService.saveWithSession(option, null)

    option.setTypeval(StatusI18nEnum.Closed.name())
    option.setId(null)
    optionValService.saveWithSession(option, null)

    option.setTypeval(StatusI18nEnum.Pending.name())
    option.setId(null)
    optionValService.saveWithSession(option, null)

    option.setType(ProjectTypeConstants.MILESTONE)
    option.setTypeval(OptionI18nEnum.MilestoneStatus.Closed.name())
    option.setId(null)
    optionValService.saveWithSession(option, null)

    option.setTypeval(OptionI18nEnum.MilestoneStatus.InProgress.name())
    option.setId(null)
    optionValService.saveWithSession(option, null)

    option.setTypeval(OptionI18nEnum.MilestoneStatus.Future.name())
    option.setId(null)
    optionValService.saveWithSession(option, null)
  }

  private def createSampleProjectData(initialUser: String, accountId: Integer): Unit = {
    val now: DateTime = new DateTime()

    val project = new Project()
    project.setSaccountid(accountId)
    project.setDescription("Sample project")
    project.setHomepage("https://www.mycollab.com")
    project.setName("Sample project")
    project.setProjectstatus(StatusI18nEnum.Open.name())
    project.setShortname("SP1")
    val projectId = projectService.saveWithSession(project, initialUser)

    val projectNotificationSetting = new ProjectNotificationSetting()
    projectNotificationSetting.setLevel(NotificationType.None.name())
    projectNotificationSetting.setProjectid(projectId)
    projectNotificationSetting.setSaccountid(accountId)
    projectNotificationSetting.setUsername(initialUser)
    projectNotificationSettingService.saveWithSession(projectNotificationSetting, initialUser)

    val message = new Message()
    message.setIsstick(true)
    message.setPosteduser(initialUser)
    message.setMessage("Welcome to MyCollab workspace. I hope you enjoy it!")
    message.setSaccountid(accountId)
    message.setProjectid(projectId)
    message.setTitle("Thank you for using MyCollab!")
    message.setPosteddate(now.toDate)
    messageService.saveWithSession(message, initialUser)

    val milestone: Milestone = new Milestone()
    milestone.setCreateduser(initialUser)
    milestone.setDeadline(now.plusDays(14).toDate)
    milestone.setStartdate(now.toDate)
    milestone.setEnddate(now.plusDays(14).toDate)
    milestone.setName("Sample milestone")
    milestone.setOwner(initialUser)
    milestone.setProjectid(projectId)
    milestone.setSaccountid(accountId)
    milestone.setStatus(MilestoneStatus.InProgress.name())
    val sampleMilestoneId = milestoneService.saveWithSession(milestone, initialUser)

    val taskA = new Task()
    taskA.setTaskname("Task A")
    taskA.setProjectid(projectId)
    taskA.setLogby(initialUser)
    taskA.setPercentagecomplete(0d)
    taskA.setPriority(TaskPriority.Medium.name())
    taskA.setSaccountid(accountId)
    taskA.setStatus(StatusI18nEnum.Open.name())
    taskA.setStartdate(now.toDate())
    taskA.setEnddate(now.plusDays(3).toDate)
    val taskAId = taskService.saveWithSession(taskA, initialUser)

    val taskB = BeanUtility.deepClone(taskA)
    taskB.setTaskname("Task B")
    taskB.setId(null)
    taskB.setMilestoneid(sampleMilestoneId)
    taskB.setStartdate(now.plusDays(2).toDate)
    taskB.setEnddate(now.plusDays(4).toDate)
    taskService.saveWithSession(taskB, initialUser)

    val taskC = BeanUtility.deepClone(taskA)
    taskC.setId(null)
    taskC.setTaskname("Task C")
    taskC.setStartdate(now.plusDays(3).toDate)
    taskC.setEnddate(now.plusDays(5).toDate)
    taskC.setParenttaskid(taskAId)
    taskService.saveWithSession(taskC, initialUser)

    val taskD = BeanUtility.deepClone(taskA)
    taskD.setId(null)
    taskD.setTaskname("Task D")
    taskD.setStartdate(now.toDate)
    taskD.setEnddate(now.plusDays(2).toDate)
    taskService.saveWithSession(taskD, initialUser)

    val component = new com.esofthead.mycollab.module.tracker.domain.Component()
    component.setComponentname("Component 1")
    component.setCreateduser(initialUser)
    component.setDescription("Sample Component 1")
    component.setStatus(StatusI18nEnum.Open.name())
    component.setProjectid(projectId)
    component.setSaccountid(accountId)
    component.setUserlead(initialUser)
    componentService.saveWithSession(component, initialUser)

    val version = new Version()
    version.setCreateduser(initialUser)
    version.setVersionname("Version 1")
    version.setDescription("Sample version")
    version.setDuedate(now.plusDays(21).toDate)
    version.setProjectid(projectId)
    version.setSaccountid(accountId)
    version.setStatus(StatusI18nEnum.Open.name())
    versionService.saveWithSession(version, initialUser)

    val bugA = new BugWithBLOBs()
    bugA.setDescription("Sample bug")
    bugA.setEnvironment("All platforms")
    bugA.setAssignuser(initialUser)
    bugA.setDuedate(now.plusDays(2).toDate)
    bugA.setLogby(initialUser)
    bugA.setMilestoneid(sampleMilestoneId)
    bugA.setSummary("Bug A")
    bugA.setStatus(BugStatus.Open.name())
    bugA.setResolution(BugResolution.Newissue.name())
    bugA.setPriority(BugPriority.Major.name())
    bugA.setProjectid(projectId)
    bugA.setSaccountid(accountId)
    val bugAId = bugService.saveWithSession(bugA, initialUser)

    val bugB = BeanUtility.deepClone(bugA)
    bugB.setId(null)
    bugB.setSummary("Bug B")
    bugB.setStatus(BugStatus.WontFix.name())
    bugB.setResolution(BugResolution.CannotReproduce.name())
    bugB.setPriority(BugPriority.Minor.name())
    bugService.saveWithSession(bugB, initialUser)

    bugRelatedService.saveAffectedVersionsOfBug(bugAId, util.Arrays.asList(version))
    bugRelatedService.saveComponentsOfBug(bugAId, util.Arrays.asList(component))

    val page = new Page()
    page.setSubject("Welcome to sample workspace")
    page.setContent("I hope you enjoy MyCollab!")
    page.setPath(PathUtils.getProjectDocumentPath(accountId, projectId) + "/" + StringUtils.generateSoftUniqueId())
    page.setStatus(WikiI18nEnum.status_public.name())
    pageService.savePage(page, initialUser)

    val folder = new Folder()
    folder.setName("Requirements")
    folder.setDescription("Sample folder")
    folder.setPath(PathUtils.getProjectDocumentPath(accountId, projectId) + "/" + StringUtils.generateSoftUniqueId())
    pageService.createFolder(folder, initialUser)
  }
}
