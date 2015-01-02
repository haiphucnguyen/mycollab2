package com.esofthead.mycollab.schedule.email.project.impl

import com.esofthead.mycollab.common.MonitorTypeConstants
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification
import com.esofthead.mycollab.configuration.StorageManager
import com.esofthead.mycollab.core.utils.StringUtils
import com.esofthead.mycollab.module.project.ProjectLinkGenerator
import com.esofthead.mycollab.module.project.domain.{SimpleMessage, SimpleProjectMember}
import com.esofthead.mycollab.module.project.i18n.MessageI18nEnum
import com.esofthead.mycollab.module.project.service.{MessageService, ProjectMemberService, ProjectService}
import com.esofthead.mycollab.schedule.email.project.MessageRelayEmailNotificationAction
import com.esofthead.mycollab.schedule.email.{ItemFieldMapper, MailContext}
import com.hp.gagawa.java.elements.Img
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
class MessageRelayEmailNotificationActionImpl extends
SendMailToAllMembersAction[SimpleMessage] with MessageRelayEmailNotificationAction {

  @Autowired var messageService: MessageService = _

  @Autowired var projectService: ProjectService = _

  @Autowired var projectMemberService: ProjectMemberService = _

  protected def getItemName: String = StringUtils.trim(bean.getTitle, 100)

  protected def getCreateSubject(context: MailContext[SimpleMessage]): String = context.getMessage(MessageI18nEnum.MAIL_CREATE_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  protected def getUpdateSubject(context: MailContext[SimpleMessage]): String = context.getMessage(MessageI18nEnum.MAIL_UPDATE_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  protected def getCommentSubject(context: MailContext[SimpleMessage]): String = context.getMessage(MessageI18nEnum.MAIL_COMMENT_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  protected def getItemFieldMapper: ItemFieldMapper = null

  protected def getBeanInContext(context: MailContext[SimpleMessage]): SimpleMessage = messageService.findById(context.getTypeid.toInt, context.getSaccountid)

  protected def buildExtraTemplateVariables(context: MailContext[SimpleMessage]) {
    val listOfTitles: List[Map[String, String]] = List[Map[String, String]]()
    val emailNotification: SimpleRelayEmailNotification = context.getEmailNotification
    val currentProject: Map[String, String] = Map[String, String]()
    currentProject.+("displayName" -> bean.getProjectName)
    currentProject.+("webLink" -> ProjectLinkGenerator.generateProjectFullLink(siteUrl, bean.getProjectid))
    listOfTitles.+:(currentProject)
    val summary: String = bean.getTitle
    val summaryLink: String = ProjectLinkGenerator.generateMessagePreviewFullLink(siteUrl, bean.getProjectid, bean.getId)
    var avatarId: String = ""
    val projectMember: SimpleProjectMember = projectMemberService.findMemberByUsername(emailNotification.getChangeby, bean.getProjectid, emailNotification.getSaccountid)
    if (projectMember != null) {
      avatarId = projectMember.getMemberAvatarId
    }
    val userAvatar: Img = new Img("", StorageManager.getAvatarLink(avatarId, 16))
    userAvatar.setWidth("16")
    userAvatar.setHeight("16")
    userAvatar.setStyle("display: inline-block; vertical-align: top;")
    val makeChangeUser: String = userAvatar.toString + emailNotification.getChangeByUserFullName
    if (MonitorTypeConstants.CREATE_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(MessageI18nEnum.MAIL_CREATE_ITEM_HEADING, makeChangeUser))
    }
    else if (MonitorTypeConstants.UPDATE_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(MessageI18nEnum.MAIL_UPDATE_ITEM_HEADING, makeChangeUser))
    }
    else if (MonitorTypeConstants.ADD_COMMENT_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(MessageI18nEnum.MAIL_COMMENT_ITEM_HEADING, makeChangeUser))
    }
    contentGenerator.putVariable("titles", listOfTitles)
    contentGenerator.putVariable("summary", summary)
    contentGenerator.putVariable("summaryLink", summaryLink)
    contentGenerator.putVariable("message", bean.getMessage)
  }
}
