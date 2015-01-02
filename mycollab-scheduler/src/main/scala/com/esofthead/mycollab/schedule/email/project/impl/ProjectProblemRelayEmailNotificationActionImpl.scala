package com.esofthead.mycollab.schedule.email.project.impl

import com.esofthead.mycollab.common.MonitorTypeConstants
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification
import com.esofthead.mycollab.common.i18n.{GenericI18Enum, OptionI18nEnum}
import com.esofthead.mycollab.configuration.StorageManager
import com.esofthead.mycollab.core.utils.StringUtils
import com.esofthead.mycollab.module.mail.MailUtils
import com.esofthead.mycollab.module.project.ProjectLinkGenerator
import com.esofthead.mycollab.module.project.domain.{Problem, SimpleProblem, SimpleProject, SimpleProjectMember}
import com.esofthead.mycollab.module.project.i18n.ProblemI18nEnum
import com.esofthead.mycollab.module.project.service.{ProblemService, ProjectMemberService, ProjectService}
import com.esofthead.mycollab.module.user.AccountLinkGenerator
import com.esofthead.mycollab.module.user.domain.SimpleUser
import com.esofthead.mycollab.module.user.service.UserService
import com.esofthead.mycollab.schedule.email.format.{DateFieldFormat, FieldFormat, I18nFieldFormat, TagBuilder}
import com.esofthead.mycollab.schedule.email.project.ProjectProblemRelayEmailNotificationAction
import com.esofthead.mycollab.schedule.email.{ItemFieldMapper, MailContext}
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.hp.gagawa.java.elements.{A, Img, Span}
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
class ProjectProblemRelayEmailNotificationActionImpl extends SendMailToAllMembersAction[SimpleProblem] with ProjectProblemRelayEmailNotificationAction {

  @Autowired var problemService: ProblemService = _

  @Autowired var projectService: ProjectService = _

  @Autowired var projectMemberService: ProjectMemberService = _

  private val mapper = new ProjectFieldNameMapper

  protected def getItemName: String = StringUtils.trim(bean.getIssuename, 100)

  protected def getCreateSubject(context: MailContext[SimpleProblem]): String = context.getMessage(ProblemI18nEnum.MAIL_CREATE_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  protected def getUpdateSubject(context: MailContext[SimpleProblem]): String = context.getMessage(ProblemI18nEnum.MAIL_UPDATE_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  protected def getCommentSubject(context: MailContext[SimpleProblem]): String = context.getMessage(ProblemI18nEnum.MAIL_COMMENT_ITEM_SUBJECT, bean.getProjectName, context.getChangeByUserFullName, getItemName)

  protected def getItemFieldMapper: ItemFieldMapper = mapper

  protected def getBeanInContext(context: MailContext[SimpleProblem]): SimpleProblem = problemService.findById(context.getTypeid.toInt, context.getSaccountid)

  protected def buildExtraTemplateVariables(context: MailContext[SimpleProblem]) {
    val listOfTitles: List[Map[String, String]] = List[Map[String, String]]()
    val emailNotification: SimpleRelayEmailNotification = context.getEmailNotification
    val relatedProject: SimpleProject = projectService.findById(bean.getProjectid, emailNotification.getSaccountid)
    val currentProject: Map[String, String] = Map[String, String]()
    currentProject.+("displayName" -> relatedProject.getName)
    currentProject.+("webLink" -> ProjectLinkGenerator.generateProjectFullLink(siteUrl, bean.getProjectid))
    listOfTitles.+:(currentProject)
    val summary: String = bean.getIssuename
    val summaryLink: String = ProjectLinkGenerator.generateProblemPreviewFullLink(siteUrl, bean.getProjectid, bean.getId)
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
      contentGenerator.putVariable("actionHeading", context.getMessage(ProblemI18nEnum.MAIL_CREATE_ITEM_HEADING, makeChangeUser))
    }
    else if (MonitorTypeConstants.UPDATE_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(ProblemI18nEnum.MAIL_UPDATE_ITEM_HEADING, makeChangeUser))
    }
    else if (MonitorTypeConstants.ADD_COMMENT_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(ProblemI18nEnum.MAIL_COMMENT_ITEM_HEADING, makeChangeUser))
    }
    contentGenerator.putVariable("titles", listOfTitles)
    contentGenerator.putVariable("summary", summary)
    contentGenerator.putVariable("summaryLink", summaryLink)
  }

  class ProjectFieldNameMapper extends ItemFieldMapper {
    put(Problem.Field.issuename, ProblemI18nEnum.FORM_NAME, true)
    put(Problem.Field.description, GenericI18Enum.FORM_DESCRIPTION, true)
    put(Problem.Field.datedue, new DateFieldFormat(Problem.Field.datedue.name, ProblemI18nEnum.FORM_DATE_DUE))
    put(Problem.Field.status, new I18nFieldFormat(Problem.Field.status.name, ProblemI18nEnum.FORM_STATUS, classOf[OptionI18nEnum.StatusI18nEnum]))
    put(Problem.Field.impact, ProblemI18nEnum.FORM_IMPACT)
    put(Problem.Field.priority, ProblemI18nEnum.FORM_PRIORITY)
    put(Problem.Field.assigntouser, new AssigneeFieldFormat(Problem.Field.assigntouser.name, GenericI18Enum.FORM_ASSIGNEE))
    put(Problem.Field.raisedbyuser, new RaisedByFieldFormat(Problem.Field.raisedbyuser.name, ProblemI18nEnum.FORM_RAISED_BY))
    put(Problem.Field.resolution, ProblemI18nEnum.FORM_RESOLUTION, true)
  }

  class AssigneeFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {

    def formatField(context: MailContext[_]): String = {
      val problem: SimpleProblem = context.getWrappedBean.asInstanceOf[SimpleProblem]
      if (problem.getAssigntouser != null) {
        val userAvatarLink: String = MailUtils.getAvatarLink(problem.getAssignUserAvatarId, 16)
        val img: Img = TagBuilder.newImg("avatar", userAvatarLink)
        val userLink: String = AccountLinkGenerator.generatePreviewFullUserLink(MailUtils.getSiteUrl(problem.getSaccountid), problem.getAssigntouser)
        val link: A = TagBuilder.newA(userLink, problem.getAssignedUserFullName)
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
        return TagBuilder.newLink(img, link).write
      }
      value
    }
  }

  class RaisedByFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {

    def formatField(context: MailContext[_]): String = {
      val problem: SimpleProblem = context.getWrappedBean.asInstanceOf[SimpleProblem]
      if (problem.getRaisedbyuser != null) {
        val userAvatarLink: String = MailUtils.getAvatarLink(problem.getRaisedByUserAvatarId, 16)
        val img: Img = TagBuilder.newImg("avatar", userAvatarLink)
        val userLink: String = AccountLinkGenerator.generatePreviewFullUserLink(MailUtils.getSiteUrl(problem.getSaccountid), problem.getRaisedbyuser)
        val link: A = TagBuilder.newA(userLink, problem.getRaisedByUserFullName)
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
