package com.esofthead.mycollab.schedule.email.project.service

import com.esofthead.mycollab.common.domain.{MailRecipientField, SimpleAuditLog, SimpleRelayEmailNotification}
import com.esofthead.mycollab.common.service.AuditLogService
import com.esofthead.mycollab.configuration.SiteConfiguration
import com.esofthead.mycollab.module.mail.MailUtils
import com.esofthead.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.esofthead.mycollab.module.project.domain.ProjectRelayEmailNotification
import com.esofthead.mycollab.module.user.domain.SimpleUser
import com.esofthead.mycollab.schedule.email.{ItemFieldMapper, MailContext, SendingRelayEmailNotificationAction}
import org.springframework.beans.factory.annotation.Autowired

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
abstract class SendMailToFollowersAction[B] extends SendingRelayEmailNotificationAction {
  @Autowired var extMailService: ExtMailService = _

  @Autowired var contentGenerator: IContentGenerator = _

  @Autowired var auditLogService: AuditLogService = _

  protected var bean: B = _

  protected var siteUrl: String = _

  def sendNotificationForCreateAction(notification: SimpleRelayEmailNotification) {
    val notifiers = getListNotifyUsersWithFilter(notification.asInstanceOf[ProjectRelayEmailNotification])
    if (notifiers != null && notifiers.nonEmpty) {
      onInitAction(notification)
      import scala.collection.JavaConversions._
      for (user <- notifiers) {
        val context = new MailContext[B](notification, user, siteUrl)
        bean = getBeanInContext(context)
        if (bean != null) {
          context.setWrappedBean(bean)
          buildExtraTemplateVariables(context)
          contentGenerator.putVariable("context", context)
          contentGenerator.putVariable("mapper", getItemFieldMapper)
          contentGenerator.putVariable("userName", user.getDisplayName)
          val userMail = new MailRecipientField(user.getEmail, user.getUsername)
          val recipients = List[MailRecipientField](userMail)
          extMailService.sendHTMLMail(SiteConfiguration.getNoReplyEmail, SiteConfiguration.getDefaultSiteName, recipients, null, null,
            contentGenerator.parseString(getCreateSubject(context)),
            contentGenerator.parseFile("templates/email/project/itemCreatedNotifier.mt", context.getLocale,
              SiteConfiguration.getDefaultLocale), null)
        }
      }
    }
  }

  def sendNotificationForUpdateAction(notification: SimpleRelayEmailNotification) {
    val notifiers = getListNotifyUsersWithFilter(notification.asInstanceOf[ProjectRelayEmailNotification])
    if (notifiers != null && notifiers.nonEmpty) {
      onInitAction(notification)
      import scala.collection.JavaConversions._
      for (user <- notifiers) {
        val context = new MailContext[B](notification, user, siteUrl)
        bean = getBeanInContext(context)
        if (bean != null) {
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
          extMailService.sendHTMLMail(SiteConfiguration.getNoReplyEmail, SiteConfiguration.getDefaultSiteName, recipients, null, null,
            contentGenerator.parseString(getUpdateSubject(context)),
            contentGenerator.parseFile("templates/email/project/itemUpdatedNotifier.mt", context.getLocale,
              SiteConfiguration.getDefaultLocale), null)
        }
      }
    }
  }

  def sendNotificationForCommentAction(notification: SimpleRelayEmailNotification) {
    val notifiers = getListNotifyUsersWithFilter(notification.asInstanceOf[ProjectRelayEmailNotification])
    if (notifiers != null && notifiers.nonEmpty) {
      onInitAction(notification)
      import scala.collection.JavaConversions._
      for (user <- notifiers) {
        val context = new MailContext[B](notification, user, siteUrl)
        bean = getBeanInContext(context)
        if (bean != null) {
          context.wrappedBean = bean
          buildExtraTemplateVariables(context)
          contentGenerator.putVariable("comment", context.getEmailNotification)
          val userMail = new MailRecipientField(user.getEmail, user.getUsername)
          val toRecipients = List[MailRecipientField](userMail)
          extMailService.sendHTMLMail(SiteConfiguration.getNoReplyEmail, SiteConfiguration.getDefaultSiteName, toRecipients,
            null, null, contentGenerator.parseString(getCommentSubject(context)),
            contentGenerator.parseFile("templates/email/project/itemCommentNotifier.mt", context.getLocale,
              SiteConfiguration.getDefaultLocale), null)
        }
      }
    }
  }

  private def onInitAction(notification: SimpleRelayEmailNotification) {
    siteUrl = MailUtils.getSiteUrl(notification.getSaccountid)
  }

  protected def getBeanInContext(context: MailContext[B]): B

  protected def getItemName: String

  protected def buildExtraTemplateVariables(emailNotification: MailContext[B])

  protected def getItemFieldMapper: ItemFieldMapper

  protected def getCreateSubject(context: MailContext[B]): String

  protected def getUpdateSubject(context: MailContext[B]): String

  protected def getCommentSubject(context: MailContext[B]): String

  protected def getListNotifyUsersWithFilter(notification: ProjectRelayEmailNotification): Set[SimpleUser]
}
