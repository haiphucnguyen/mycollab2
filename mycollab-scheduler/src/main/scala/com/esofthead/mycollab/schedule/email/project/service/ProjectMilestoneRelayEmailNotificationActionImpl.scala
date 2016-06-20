package com.esofthead.mycollab.schedule.email.project.service

import com.esofthead.mycollab.common.MonitorTypeConstants
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.core.utils.StringUtils
import com.esofthead.mycollab.html.{FormatUtils, LinkUtils}
import com.esofthead.mycollab.module.mail.MailUtils
import com.esofthead.mycollab.module.project.ProjectLinkGenerator
import com.esofthead.mycollab.module.project.domain.{Milestone, SimpleMilestone}
import com.esofthead.mycollab.module.project.i18n.{MilestoneI18nEnum, OptionI18nEnum}
import com.esofthead.mycollab.module.project.service.{MilestoneService, ProjectService}
import com.esofthead.mycollab.module.user.AccountLinkGenerator
import com.esofthead.mycollab.module.user.service.UserService
import com.esofthead.mycollab.schedule.email.format._
import com.esofthead.mycollab.schedule.email.project.ProjectMilestoneRelayEmailNotificationAction
import com.esofthead.mycollab.schedule.email.{ItemFieldMapper, MailContext}
import com.esofthead.mycollab.spring.AppContextUtil
import com.hp.gagawa.java.elements.Span
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class ProjectMilestoneRelayEmailNotificationActionImpl extends SendMailToAllMembersAction[SimpleMilestone] with ProjectMilestoneRelayEmailNotificationAction {

  @Autowired var milestoneService: MilestoneService = _

  @Autowired var projectService: ProjectService = _

  private val mapper = new MilestoneFieldNameMapper

  override protected def getItemName: String = StringUtils.trim(bean.getName, 100)

  override protected def getCreateSubject(context: MailContext[SimpleMilestone]): String = context.getMessage(
    MilestoneI18nEnum.MAIL_CREATE_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  override protected def getUpdateSubject(context: MailContext[SimpleMilestone]): String = context.getMessage(
    MilestoneI18nEnum.MAIL_UPDATE_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  override protected def getCommentSubject(context: MailContext[SimpleMilestone]): String = context.getMessage(
    MilestoneI18nEnum.MAIL_COMMENT_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  override protected def getItemFieldMapper: ItemFieldMapper = mapper

  override protected def getBeanInContext(context: MailContext[SimpleMilestone]): SimpleMilestone = milestoneService.
    findById(context.getTypeid.toInt, context.getSaccountid)

  class MilestoneFieldNameMapper extends ItemFieldMapper {
    put(Milestone.Field.name, GenericI18Enum.FORM_NAME, isColSpan = true)
    put(Milestone.Field.status, new I18nFieldFormat(Milestone.Field.status.name, GenericI18Enum.FORM_STATUS,
      classOf[OptionI18nEnum.MilestoneStatus]))
    put(Milestone.Field.owner, new AssigneeFieldFormat(Milestone.Field.owner.name, GenericI18Enum.FORM_ASSIGNEE))
    put(Milestone.Field.startdate, new DateFieldFormat(Milestone.Field.startdate.name, GenericI18Enum.FORM_START_DATE))
    put(Milestone.Field.enddate, new DateFieldFormat(Milestone.Field.enddate.name, GenericI18Enum.FORM_END_DATE))
    put(Milestone.Field.description, GenericI18Enum.FORM_DESCRIPTION, isColSpan = true)
  }

  class AssigneeFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {

    def formatField(context: MailContext[_]): String = {
      val milestone = context.getWrappedBean.asInstanceOf[SimpleMilestone]
      if (milestone.getOwner != null) {
        val userAvatarLink = MailUtils.getAvatarLink(milestone.getOwnerAvatarId, 16)
        val img = FormatUtils.newImg("avatar", userAvatarLink)
        val userLink = AccountLinkGenerator.generatePreviewFullUserLink(MailUtils.getSiteUrl(milestone.getSaccountid),
          milestone.getOwner)
        val link = FormatUtils.newA(userLink, milestone.getOwnerFullName)
        FormatUtils.newLink(img, link).write
      }
      else {
        new Span().write
      }
    }

    def formatField(context: MailContext[_], value: String): String = {
      if (StringUtils.isBlank(value)) {
        return new Span().write
      }
      val userService = AppContextUtil.getSpringBean(classOf[UserService])
      val user = userService.findUserByUserNameInAccount(value, context.getUser.getAccountId)
      if (user != null) {
        val userAvatarLink = MailUtils.getAvatarLink(user.getAvatarid, 16)
        val userLink = AccountLinkGenerator.generatePreviewFullUserLink(MailUtils.getSiteUrl(user.getAccountId),
          user.getUsername)
        val img = FormatUtils.newImg("avatar", userAvatarLink)
        val link = FormatUtils.newA(userLink, user.getDisplayName)
        FormatUtils.newLink(img, link).write
      } else
        value
    }
  }

  override protected def buildExtraTemplateVariables(context: MailContext[SimpleMilestone]) {
    val emailNotification = context.getEmailNotification
    val relatedProject = projectService.findById(bean.getProjectid, emailNotification.getSaccountid)

    val projectHyperLink = new WebItem(relatedProject.getName, ProjectLinkGenerator.generateProjectFullLink(siteUrl,
      bean.getProjectid))

    val summary = bean.getName
    val summaryLink = ProjectLinkGenerator.generateMilestonePreviewFullLink(siteUrl, bean.getProjectid, bean.getId)

    val projectMember = projectMemberService.findMemberByUsername(emailNotification.getChangeby,
      bean.getProjectid, emailNotification.getSaccountid)
    val avatarId = if (projectMember != null) projectMember.getMemberAvatarId else ""
    val userAvatar = LinkUtils.newAvatar(avatarId)

    val makeChangeUser = userAvatar.toString + emailNotification.getChangeByUserFullName
    val actionEnum = emailNotification.getAction match {
      case MonitorTypeConstants.CREATE_ACTION => MilestoneI18nEnum.MAIL_CREATE_ITEM_HEADING
      case MonitorTypeConstants.UPDATE_ACTION => MilestoneI18nEnum.MAIL_UPDATE_ITEM_HEADING
      case MonitorTypeConstants.ADD_COMMENT_ACTION => MilestoneI18nEnum.MAIL_COMMENT_ITEM_HEADING
    }

    contentGenerator.putVariable("actionHeading", context.getMessage(actionEnum, makeChangeUser))
    contentGenerator.putVariable("projectHyperLink", projectHyperLink)
    contentGenerator.putVariable("summary", summary)
    contentGenerator.putVariable("summaryLink", summaryLink)
  }
}
