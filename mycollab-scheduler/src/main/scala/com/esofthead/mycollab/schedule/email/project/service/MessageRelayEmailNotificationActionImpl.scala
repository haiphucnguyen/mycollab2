package com.esofthead.mycollab.schedule.email.project.service

import com.esofthead.mycollab.common.MonitorTypeConstants
import com.esofthead.mycollab.core.utils.StringUtils
import com.esofthead.mycollab.html.LinkUtils
import com.esofthead.mycollab.module.project.ProjectLinkGenerator
import com.esofthead.mycollab.module.project.domain.SimpleMessage
import com.esofthead.mycollab.module.project.i18n.MessageI18nEnum
import com.esofthead.mycollab.module.project.service.{MessageService, ProjectService}
import com.esofthead.mycollab.schedule.email.format.WebItem
import com.esofthead.mycollab.schedule.email.project.MessageRelayEmailNotificationAction
import com.esofthead.mycollab.schedule.email.{ItemFieldMapper, MailContext}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class MessageRelayEmailNotificationActionImpl extends SendMailToAllMembersAction[SimpleMessage] with MessageRelayEmailNotificationAction {
  @Autowired var messageService: MessageService = _
  @Autowired var projectService: ProjectService = _

  protected def getItemName: String = StringUtils.trim(bean.getTitle, 100)

  protected def getCreateSubject(context: MailContext[SimpleMessage]): String = context.getMessage(
    MessageI18nEnum.MAIL_CREATE_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  protected def getUpdateSubject(context: MailContext[SimpleMessage]): String = context.getMessage(
    MessageI18nEnum.MAIL_UPDATE_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  protected def getCommentSubject(context: MailContext[SimpleMessage]): String = context.getMessage(
    MessageI18nEnum.MAIL_COMMENT_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  protected def getItemFieldMapper: ItemFieldMapper = null

  protected def getBeanInContext(context: MailContext[SimpleMessage]): SimpleMessage = messageService.findById(context.getTypeid.toInt,
    context.getSaccountid)

  protected def buildExtraTemplateVariables(context: MailContext[SimpleMessage]) {
    val currentProject = new WebItem(bean.getProjectName, ProjectLinkGenerator.generateProjectFullLink(siteUrl, bean.getProjectid))
    val emailNotification = context.getEmailNotification

    val summary = bean.getTitle
    val summaryLink = ProjectLinkGenerator.generateMessagePreviewFullLink(siteUrl, bean.getProjectid, bean.getId)
    val projectMember = projectMemberService.findMemberByUsername(emailNotification.getChangeby,
      bean.getProjectid, emailNotification.getSaccountid)

    val avatarId = if (projectMember != null) projectMember.getMemberAvatarId else ""
    val userAvatar = LinkUtils.newAvatar(avatarId)

    val makeChangeUser = userAvatar.toString + emailNotification.getChangeByUserFullName
    val actionEnum = emailNotification.getAction match {
      case MonitorTypeConstants.CREATE_ACTION => MessageI18nEnum.MAIL_CREATE_ITEM_HEADING
      case MonitorTypeConstants.UPDATE_ACTION => MessageI18nEnum.MAIL_UPDATE_ITEM_HEADING
      case MonitorTypeConstants.ADD_COMMENT_ACTION => MessageI18nEnum.MAIL_COMMENT_ITEM_HEADING
    }

    contentGenerator.putVariable("actionHeading", context.getMessage(actionEnum, makeChangeUser))
    contentGenerator.putVariable("titles", List(currentProject))
    contentGenerator.putVariable("summary", summary)
    contentGenerator.putVariable("summaryLink", summaryLink)
    contentGenerator.putVariable("message", bean.getMessage)
  }
}
