package com.esofthead.mycollab.schedule.jobs

import java.util

import com.esofthead.mycollab.common.NotificationType
import com.esofthead.mycollab.common.domain.MailRecipientField
import com.esofthead.mycollab.configuration.SiteConfiguration
import com.esofthead.mycollab.core.arguments.{NumberSearchField, RangeDateSearchField, SetSearchField}
import com.esofthead.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria
import com.esofthead.mycollab.module.project.service.{ProjectGenericTaskService, ProjectMemberService, ProjectNotificationSettingService}
import com.esofthead.mycollab.module.user.domain.SimpleUser
import org.joda.time.LocalDate
import org.quartz.{JobExecutionContext, JobExecutionException}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.2.6
  */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class OverdueProjectAssignmentsNotificationJob extends GenericQuartzJobBean {

  @Autowired private var projectGenericTaskService: ProjectGenericTaskService = _

  @Autowired private var extMailService: ExtMailService = _

  @Autowired private var contentGenerator: IContentGenerator = _

  @Autowired private var projectMemberService: ProjectMemberService = _

  @Autowired private var projectNotificationService: ProjectNotificationSettingService = _

  @throws(classOf[JobExecutionException])
  override protected def executeJob(context: JobExecutionContext): Unit = {
    val searchCriteria = new ProjectGenericTaskSearchCriteria
    searchCriteria.setSaccountid(null)
    val now = new LocalDate()
    val past = now.minusDays(10000)
    val rangeDate = new RangeDateSearchField(past.toDate, now.toDate)
    searchCriteria.setDateInRange(rangeDate)
    import scala.collection.JavaConverters._
    val accountIds = projectGenericTaskService.getAccountsHasOverdueAssignments(searchCriteria).asScala.toList
    if (accountIds != null) {
      for (accountId <- accountIds) {
        searchCriteria.setSaccountid(new NumberSearchField(accountId))
        import scala.collection.JavaConverters._
        val projectIds = projectGenericTaskService.getProjectsHasOverdueAssignments(searchCriteria).asScala.toList
        for (projectId <- projectIds) {
          searchCriteria.setProjectIds(new SetSearchField[Integer](projectId))
          val assignments = projectGenericTaskService.findAbsoluteListByCriteria(searchCriteria, 0, Integer.MAX_VALUE).asScala.toList
          val notifiers = getNotifiersOfProject(projectId, accountId)
          for (notifier <- notifiers) {
            contentGenerator.putVariable("assignments", assignments)
            val userMail = new MailRecipientField(notifier.getEmail, notifier.getDisplayName)
            val recipients = util.Arrays.asList(userMail)
            extMailService.sendHTMLMail(SiteConfiguration.getNoReplyEmail, SiteConfiguration.getDefaultSiteName, recipients,
              null, null,
              contentGenerator.parseString("Overdue assignments"),
              contentGenerator.parseFile("templates/email/project/itemCreatedNotifier.mt", SiteConfiguration.getDefaultLocale), null)
          }
        }
      }
    }
  }

  private def getNotifiersOfProject(projectId: Integer, accountId: Integer): Set[SimpleUser] = {
    import scala.collection.JavaConverters._
    var notifyUsers: Set[SimpleUser] = projectMemberService.getActiveUsersInProject(projectId, accountId).asScala.toSet
    val notificationSettings: List[ProjectNotificationSetting] = projectNotificationService.findNotifications(projectId, accountId).asScala.toList
    if (notificationSettings.nonEmpty) {
      for (setting <- notificationSettings) {
        if ((NotificationType.None.name == setting.getLevel) || (NotificationType.Minimal.name == setting.getLevel)) {
          notifyUsers = notifyUsers.filter(notifyUser => !(notifyUser.getUsername == setting.getUsername))
        }
      }
    }
    return notifyUsers
  }
}
