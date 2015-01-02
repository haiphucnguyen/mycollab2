package com.esofthead.mycollab.schedule.email.project.impl

import com.esofthead.mycollab.common.MonitorTypeConstants
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification
import com.esofthead.mycollab.common.i18n.{GenericI18Enum, OptionI18nEnum}
import com.esofthead.mycollab.configuration.StorageManager
import com.esofthead.mycollab.core.utils.StringUtils
import com.esofthead.mycollab.module.project.ProjectLinkGenerator
import com.esofthead.mycollab.module.project.domain.{SimpleProject, SimpleProjectMember}
import com.esofthead.mycollab.module.project.i18n.VersionI18nEnum
import com.esofthead.mycollab.module.project.service.{ProjectMemberService, ProjectService}
import com.esofthead.mycollab.module.tracker.domain.{SimpleVersion, Version}
import com.esofthead.mycollab.module.tracker.service.VersionService
import com.esofthead.mycollab.schedule.email.format.{DateFieldFormat, I18nFieldFormat}
import com.esofthead.mycollab.schedule.email.project.VersionRelayEmailNotificationAction
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
class VersionRelayEmailNotificationActionImpl extends SendMailToAllMembersAction[SimpleVersion] with VersionRelayEmailNotificationAction {

  @Autowired var versionService: VersionService = _

  @Autowired var projectService: ProjectService = _

  @Autowired var projectMemberService: ProjectMemberService = _

  private val mapper = new VersionFieldNameMapper

  protected def buildExtraTemplateVariables(context: MailContext[SimpleVersion]) {
    val listOfTitles: List[Map[String, String]] = List[Map[String, String]]()
    val emailNotification: SimpleRelayEmailNotification = context.getEmailNotification
    val currentProject: Map[String, String] = Map[String, String]()
    val project: SimpleProject = projectService.findById(bean.getProjectid, emailNotification.getSaccountid)
    currentProject.+("displayName" -> project.getName)
    currentProject.+("webLink" -> ProjectLinkGenerator.generateProjectFullLink(siteUrl, bean.getProjectid))
    listOfTitles.+:(currentProject)
    val summary: String = bean.getVersionname
    val summaryLink: String = ProjectLinkGenerator.generateBugComponentPreviewFullLink(siteUrl, bean.getProjectid, bean.getId)
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
      contentGenerator.putVariable("actionHeading", context.getMessage(VersionI18nEnum.MAIL_CREATE_ITEM_HEADING, makeChangeUser))
    }
    else if (MonitorTypeConstants.UPDATE_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(VersionI18nEnum.MAIL_UPDATE_ITEM_HEADING, makeChangeUser))
    }
    else if (MonitorTypeConstants.ADD_COMMENT_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(VersionI18nEnum.MAIL_COMMENT_ITEM_HEADING, makeChangeUser))
    }
    contentGenerator.putVariable("titles", listOfTitles)
    contentGenerator.putVariable("summary", summary)
    contentGenerator.putVariable("summaryLink", summaryLink)
  }

  protected def getItemName: String = StringUtils.trim(bean.getDescription, 100)

  protected def getCreateSubject(context: MailContext[SimpleVersion]): String = context.getMessage(VersionI18nEnum.MAIL_CREATE_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  protected def getUpdateSubject(context: MailContext[SimpleVersion]): String = context.getMessage(VersionI18nEnum.MAIL_UPDATE_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  protected def getCommentSubject(context: MailContext[SimpleVersion]): String = context.getMessage(VersionI18nEnum.MAIL_COMMENT_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  protected def getItemFieldMapper: ItemFieldMapper = mapper

  protected def getBeanInContext(context: MailContext[SimpleVersion]): SimpleVersion = versionService.findById(context.getTypeid.toInt, context.getSaccountid)

  class VersionFieldNameMapper extends ItemFieldMapper {
    put(Version.Field.description, GenericI18Enum.FORM_DESCRIPTION, true)
    put(Version.Field.status, new I18nFieldFormat(Version.Field.status.name, VersionI18nEnum.FORM_STATUS, classOf[OptionI18nEnum.StatusI18nEnum]))
    put(Version.Field.versionname, VersionI18nEnum.FORM_NAME)
    put(Version.Field.duedate, new DateFieldFormat(Version.Field.duedate.name, VersionI18nEnum.FORM_DUE_DATE))
  }

}
