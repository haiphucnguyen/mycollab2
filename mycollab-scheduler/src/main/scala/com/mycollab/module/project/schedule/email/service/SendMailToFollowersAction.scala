package com.mycollab.module.project.schedule.email.service

import com.mycollab.common.domain.criteria.CommentSearchCriteria
import com.mycollab.common.domain.{MailRecipientField, SimpleRelayEmailNotification}
import com.mycollab.common.service.{AuditLogService, CommentService}
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.db.arguments.{BasicSearchRequest, StringSearchField}
import com.mycollab.module.mail.MailUtils
import com.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.mycollab.module.project.ProjectLinkGenerator
import com.mycollab.module.project.domain.{ProjectRelayEmailNotification, SimpleProjectMember}
import com.mycollab.module.project.service.{ProjectMemberService, ProjectService}
import com.mycollab.module.user.domain.SimpleUser
import com.mycollab.schedule.email.format.WebItem
import com.mycollab.schedule.email.{ItemFieldMapper, MailContext, SendingRelayEmailNotificationAction}
import org.springframework.beans.factory.annotation.Autowired

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
abstract class SendMailToFollowersAction[B] extends SendingRelayEmailNotificationAction {
  @Autowired var extMailService: ExtMailService = _
  @Autowired var projectService: ProjectService = _
  @Autowired var projectMemberService: ProjectMemberService = _
  @Autowired val commentService: CommentService = null
  @Autowired var contentGenerator: IContentGenerator = _
  @Autowired var auditLogService: AuditLogService = _
  
  protected var bean: B = _
  protected var projectMember: SimpleProjectMember = _
  protected var siteUrl: String = _
  
  def sendNotificationForCreateAction(notification: SimpleRelayEmailNotification) {
    val projectRelayEmailNotification = notification.asInstanceOf[ProjectRelayEmailNotification]
    val notifiers = getListNotifyUsersWithFilter(projectRelayEmailNotification)
    if (notifiers != null && notifiers.nonEmpty) {
      onInitAction(projectRelayEmailNotification)
      bean = getBeanInContext(projectRelayEmailNotification)
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
    val projectRelayEmailNotification = notification.asInstanceOf[ProjectRelayEmailNotification]
    val notifiers = getListNotifyUsersWithFilter(projectRelayEmailNotification)
    if (notifiers != null && notifiers.nonEmpty) {
      onInitAction(projectRelayEmailNotification)
      bean = getBeanInContext(projectRelayEmailNotification)
      if (bean != null) {
        import scala.collection.JavaConversions._
        val auditLog = auditLogService.findLastestLog(notification.getTypeid.toInt, notification.getSaccountid)
        contentGenerator.putVariable("historyLog", auditLog)
        contentGenerator.putVariable("mapper", getItemFieldMapper)
        val searchCriteria: CommentSearchCriteria = new CommentSearchCriteria
        searchCriteria.setType(StringSearchField.and(notification.getType))
        searchCriteria.setTypeId(StringSearchField.and(notification.getTypeid))
        searchCriteria.setSaccountid(null)
        val comments = commentService.findPagableListByCriteria(new BasicSearchRequest[CommentSearchCriteria](searchCriteria, 0, 5))
        contentGenerator.putVariable("lastComments", comments)
        
        for (user <- notifiers) {
          val context = new MailContext[B](notification, user, siteUrl)
          context.setWrappedBean(bean)
          buildExtraTemplateVariables(context)
          contentGenerator.putVariable("context", context)
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
    val projectRelayEmailNotification = notification.asInstanceOf[ProjectRelayEmailNotification]
    val notifiers = getListNotifyUsersWithFilter(projectRelayEmailNotification)
    if (notifiers != null && notifiers.nonEmpty) {
      onInitAction(projectRelayEmailNotification)
      bean = getBeanInContext(projectRelayEmailNotification)
      if (bean != null) {
        import scala.collection.JavaConversions._
        val searchCriteria: CommentSearchCriteria = new CommentSearchCriteria
        searchCriteria.setType(StringSearchField.and(notification.getType))
        searchCriteria.setTypeId(StringSearchField.and(notification.getTypeid))
        searchCriteria.setSaccountid(null)
        val comments = commentService.findPagableListByCriteria(new BasicSearchRequest[CommentSearchCriteria](searchCriteria, 0, 5))
        contentGenerator.putVariable("lastComments", comments)
        
        for (user <- notifiers) {
          val context = new MailContext[B](notification, user, siteUrl)
          context.wrappedBean = bean
          buildExtraTemplateVariables(context)
          contentGenerator.putVariable("comment", context.getEmailNotification)
          val userMail = new MailRecipientField(user.getEmail, user.getUsername)
          val toRecipients = List[MailRecipientField](userMail)
          extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName, toRecipients,
            null, null, getCommentSubject(context),
            contentGenerator.parseFile("mailProjectItemCommentNotifier.ftl", context.getLocale), null)
        }
      }
    }
  }
  
  private def onInitAction(notification: ProjectRelayEmailNotification) {
    siteUrl = MailUtils.getSiteUrl(notification.getSaccountid)
    val relatedProject = projectService.findById(notification.getProjectId, notification.getSaccountid)
    val projectHyperLink = new WebItem(relatedProject.getName, ProjectLinkGenerator.generateProjectFullLink(siteUrl, relatedProject.getId))
    contentGenerator.putVariable("projectHyperLink", projectHyperLink)
    projectMember = projectMemberService.findMemberByUsername(notification.getChangeby, notification.getProjectId,
      notification.getSaccountid)
  }
  
  protected def getBeanInContext(notification: ProjectRelayEmailNotification): B
  
  protected def getItemName: String
  
  protected def buildExtraTemplateVariables(emailNotification: MailContext[B])
  
  protected def getItemFieldMapper: ItemFieldMapper
  
  protected def getCreateSubject(context: MailContext[B]): String
  
  protected def getUpdateSubject(context: MailContext[B]): String
  
  protected def getCommentSubject(context: MailContext[B]): String
  
  protected def getListNotifyUsersWithFilter(notification: ProjectRelayEmailNotification): Set[SimpleUser]
}
