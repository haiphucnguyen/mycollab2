package com.esofthead.mycollab.schedule.email.project.impl

import com.esofthead.mycollab.common.MonitorTypeConstants
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification
import com.esofthead.mycollab.common.i18n.{GenericI18Enum, OptionI18nEnum}
import com.esofthead.mycollab.configuration.StorageManager
import com.esofthead.mycollab.core.utils.StringUtils
import com.esofthead.mycollab.module.mail.MailUtils
import com.esofthead.mycollab.module.project.ProjectLinkGenerator
import com.esofthead.mycollab.module.project.domain.{SimpleProject, SimpleProjectMember}
import com.esofthead.mycollab.module.project.i18n.ComponentI18nEnum
import com.esofthead.mycollab.module.project.service.{ProjectMemberService, ProjectService}
import com.esofthead.mycollab.module.tracker.domain.{Component, SimpleComponent}
import com.esofthead.mycollab.module.tracker.service.ComponentService
import com.esofthead.mycollab.module.user.AccountLinkGenerator
import com.esofthead.mycollab.module.user.domain.SimpleUser
import com.esofthead.mycollab.module.user.service.UserService
import com.esofthead.mycollab.schedule.email.format.{FieldFormat, I18nFieldFormat, TagBuilder}
import com.esofthead.mycollab.schedule.email.project.ComponentRelayEmailNotificationAction
import com.esofthead.mycollab.schedule.email.{ItemFieldMapper, MailContext}
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.hp.gagawa.java.elements.{A, Img, Span}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE) class ComponentRelayEmailNotificationActionImpl extends SendMailToAllMembersAction[SimpleComponent] with ComponentRelayEmailNotificationAction {


  @Autowired var componentService: ComponentService = _

  @Autowired var projectService: ProjectService = _

  @Autowired var projectMemberService: ProjectMemberService = _

  private val mapper: ComponentFieldNameMapper = new ComponentFieldNameMapper

  protected def buildExtraTemplateVariables(context: MailContext[SimpleComponent]) {
    val listOfTitles: List[Map[String, String]] = List[Map[String, String]]()
    val emailNotification: SimpleRelayEmailNotification = context.getEmailNotification
    var currentProject: Map[String, String] = Map[String, String]()
    val project: SimpleProject = projectService.findById(bean.getProjectid, emailNotification.getSaccountid)
    currentProject += ("displayName" -> project.getName)
    currentProject += ("webLink" -> ProjectLinkGenerator.generateProjectFullLink(siteUrl, bean.getProjectid))
    listOfTitles.+:(currentProject)
    val summary: String = bean.getComponentname
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
      contentGenerator.putVariable("actionHeading", context.getMessage(ComponentI18nEnum.MAIL_CREATE_ITEM_HEADING, makeChangeUser))
    }
    else if (MonitorTypeConstants.UPDATE_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(ComponentI18nEnum.MAIL_UPDATE_ITEM_HEADING, makeChangeUser))
    }
    else if (MonitorTypeConstants.ADD_COMMENT_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(ComponentI18nEnum.MAIL_COMMENT_ITEM_HEADING, makeChangeUser))
    }
    contentGenerator.putVariable("titles", listOfTitles)
    contentGenerator.putVariable("summary", summary)
    contentGenerator.putVariable("summaryLink", summaryLink)
  }

  protected def getUpdateSubject(context: MailContext[SimpleComponent]): String = context.getMessage(ComponentI18nEnum.MAIL_UPDATE_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  protected def getBeanInContext(context: MailContext[SimpleComponent]): SimpleComponent = componentService.findById(context.getTypeid.toInt, context.getSaccountid)

  protected def getItemName: String = StringUtils.trim(bean.getDescription, 100)

  protected def getCreateSubject(context: MailContext[SimpleComponent]): String = context.getMessage(ComponentI18nEnum.MAIL_CREATE_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  protected def getCommentSubject(context: MailContext[SimpleComponent]): String = context.getMessage(ComponentI18nEnum.MAIL_COMMENT_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  protected def getItemFieldMapper: ItemFieldMapper = mapper

  class ComponentFieldNameMapper extends ItemFieldMapper {
    put(Component.Field.description, GenericI18Enum.FORM_DESCRIPTION, true)
    put(Component.Field.status, new I18nFieldFormat(Component.Field.status.name, ComponentI18nEnum.FORM_STATUS, classOf[OptionI18nEnum.StatusI18nEnum]))
    put(Component.Field.userlead, new LeadFieldFormat(Component.Field.userlead.name, ComponentI18nEnum.FORM_LEAD))
  }

  class LeadFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {

    def formatField(context: MailContext[_]): String = {
      val component: SimpleComponent = context.getWrappedBean.asInstanceOf[SimpleComponent]
      if (component.getUserlead != null) {
        val userAvatarLink: String = MailUtils.getAvatarLink(component.getUserLeadAvatarId, 16)
        val img: Img = TagBuilder.newImg("avatar", userAvatarLink)
        val userLink: String = AccountLinkGenerator.generatePreviewFullUserLink(MailUtils.getSiteUrl(component.getSaccountid), component.getUserlead)
        val link: A = TagBuilder.newA(userLink, component.getUserLeadFullName)
        TagBuilder.newLink(img, link).write
      }
      else {
        new Span().write
      }
    }

    def formatField(context: MailContext[_], value: String): String = {
      if (org.apache.commons.lang3.StringUtils.isBlank(value)) {
        new Span().write
      }
      val userService: UserService = ApplicationContextUtil.getSpringBean(classOf[UserService])
      val user: SimpleUser = userService.findUserByUserNameInAccount(value, context.getUser.getAccountId)
      if (user != null) {
        val userAvatarLink: String = MailUtils.getAvatarLink(user.getAvatarid, 16)
        val userLink: String = AccountLinkGenerator.generatePreviewFullUserLink(MailUtils.getSiteUrl(user.getAccountId), user.getUsername)
        val img: Img = TagBuilder.newImg("avatar", userAvatarLink)
        val link: A = TagBuilder.newA(userLink, user.getDisplayName)
        TagBuilder.newLink(img, link).write
      }
      value
    }
  }

}
