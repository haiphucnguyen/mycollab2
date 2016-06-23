package com.esofthead.mycollab.schedule.email.project.service

import com.esofthead.mycollab.common.NotificationType
import com.esofthead.mycollab.common.domain.{MailRecipientField, SimpleRelayEmailNotification}
import com.esofthead.mycollab.common.service.AuditLogService
import com.esofthead.mycollab.configuration.SiteConfiguration
import com.esofthead.mycollab.module.mail.MailUtils
import com.esofthead.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.esofthead.mycollab.module.project.domain.ProjectRelayEmailNotification
import com.esofthead.mycollab.module.project.service.{ProjectMemberService, ProjectNotificationSettingService}
import com.esofthead.mycollab.module.user.domain.SimpleUser
import com.esofthead.mycollab.schedule.email.{ItemFieldMapper, MailContext, SendingRelayEmailNotificationAction}
import org.springframework.beans.factory.annotation.Autowired

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
abstract class SendMailToAllMembersAction[B] extends SendingRelayEmailNotificationAction {
  @Autowired var extMailService: ExtMailService = _
  @Autowired var projectMemberService: ProjectMemberService = _
  @Autowired var projectNotificationService: ProjectNotificationSettingService = _
  @Autowired var auditLogService: AuditLogService = _
  @Autowired protected var contentGenerator: IContentGenerator = _

  protected var bean: B = _

  protected var siteUrl: String = _

  private def getNotifyUsers(notification: ProjectRelayEmailNotification): Set[SimpleUser] = {
    import scala.collection.JavaConverters._
    var notifyUsers = projectMemberService.getActiveUsersInProject(notification.getProjectId,
      notification.getSaccountid).asScala.toSet
    val notificationSettings = projectNotificationService.findNotifications(notification.getProjectId,
      notification.getSaccountid).asScala.toList
    if (notificationSettings.nonEmpty) {
      for (setting <- notificationSettings) {
        if ((NotificationType.None.name == setting.getLevel) || (NotificationType.Minimal.name == setting.getLevel)) {
          notifyUsers = notifyUsers.filter(notifyUser => !(notifyUser.getUsername == setting.getUsername))
        }
      }
    }
    notifyUsers
  }

  def sendNotificationForCreateAction(notification: SimpleRelayEmailNotification) {
    val notifiers = getNotifyUsers(notification.asInstanceOf[ProjectRelayEmailNotification])
    if (notifiers != null && notifiers.nonEmpty) {
      onInitAction(notification)
      bean = getBeanInContext(notification.asInstanceOf[ProjectRelayEmailNotification])
      if (bean != null) {
        import scala.collection.JavaConversions._
        for (user <- notifiers) {
          val context = new MailContext[B](notification, user, siteUrl)
          context.setWrappedBean(bean)
          buildExtraTemplateVariables(context)
          contentGenerator.putVariable("context", context)
          contentGenerator.putVariable("mapper", getItemFieldMapper)
          contentGenerator.putVariable("userName", user.getDisplayName)
          val userMail = new MailRecipientField(user.getEmail, user.getUsername)
          val recipients = List[MailRecipientField](userMail)
          extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName, recipients,
            null, null, getCreateSubject(context),
            contentGenerator.parseFile("mailProjectItemCreatedNotifier.ftl", context.getLocale), null)
        }
      }
    }
  }

  def sendNotificationForUpdateAction(notification: SimpleRelayEmailNotification) {
    val notifiers = getNotifyUsers(notification.asInstanceOf[ProjectRelayEmailNotification])
    if (notifiers != null && notifiers.nonEmpty) {
      onInitAction(notification)
      bean = getBeanInContext(notification.asInstanceOf[ProjectRelayEmailNotification])
      if (bean != null) {
        import scala.collection.JavaConversions._
        for (user <- notifiers) {
          val context = new MailContext[B](notification, user, siteUrl)
          context.setWrappedBean(bean)
          buildExtraTemplateVariables(context)
          if (context.getTypeid != null) {
            val auditLog = auditLogService.findLastestLog(context.getTypeid.toInt, context.getSaccountid)
            contentGenerator.putVariable("historyLog", auditLog)
            contentGenerator.putVariable("context", context)
            contentGenerator.putVariable("mapper", getItemFieldMapper)
          }
          val userMail = new MailRecipientField(user.getEmail, user.getUsername)
          val recipients = List[MailRecipientField](userMail)
          extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName, recipients,
            null, null, getUpdateSubject(context),
            contentGenerator.parseFile("mailProjectItemUpdatedNotifier.ftl", context.getLocale), null)
        }
      }
    }
  }

  def sendNotificationForCommentAction(notification: SimpleRelayEmailNotification) {
    val notifiers = getNotifyUsers(notification.asInstanceOf[ProjectRelayEmailNotification])
    if (notifiers != null && notifiers.nonEmpty) {
      onInitAction(notification)
      bean = getBeanInContext(notification.asInstanceOf[ProjectRelayEmailNotification])
      if (bean != null) {
        import scala.collection.JavaConversions._
        for (user <- notifiers) {
          val context = new MailContext[B](notification, user, siteUrl)
          buildExtraTemplateVariables(context)
          contentGenerator.putVariable("comment", context.getEmailNotification)
          val userMail = new MailRecipientField(user.getEmail, user.getUsername)
          val recipients = List[MailRecipientField](userMail)
          extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName, recipients,
            null, null, getCommentSubject(context),
            contentGenerator.parseFile("mailProjectItemCommentNotifier.ftl", context.getLocale), null)
        }
      }
    }
  }

  private def onInitAction(notification: SimpleRelayEmailNotification): Unit = {
    siteUrl = MailUtils.getSiteUrl(notification.getSaccountid)
  }

  protected def getBeanInContext(notification: ProjectRelayEmailNotification): B

  protected def buildExtraTemplateVariables(context: MailContext[B])

  protected def getItemName: String

  protected def getCreateSubject(context: MailContext[B]): String

  protected def getUpdateSubject(context: MailContext[B]): String

  protected def getCommentSubject(context: MailContext[B]): String

  protected def getItemFieldMapper: ItemFieldMapper
}
