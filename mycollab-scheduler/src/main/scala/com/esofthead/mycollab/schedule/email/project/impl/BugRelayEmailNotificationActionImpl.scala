package com.esofthead.mycollab.schedule.email.project.impl

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.common.{MonitorTypeConstants, NotificationType}
import com.esofthead.mycollab.configuration.StorageManager
import com.esofthead.mycollab.core.utils.StringUtils
import com.esofthead.mycollab.module.mail.MailUtils
import com.esofthead.mycollab.module.project.domain._
import com.esofthead.mycollab.module.project.i18n.{BugI18nEnum, OptionI18nEnum}
import com.esofthead.mycollab.module.project.service.{MilestoneService, ProjectMemberService, ProjectNotificationSettingService, ProjectService}
import com.esofthead.mycollab.module.project.{ProjectLinkGenerator, ProjectResources, ProjectTypeConstants}
import com.esofthead.mycollab.module.tracker.domain.{BugWithBLOBs, SimpleBug}
import com.esofthead.mycollab.module.tracker.service.BugService
import com.esofthead.mycollab.module.user.AccountLinkGenerator
import com.esofthead.mycollab.module.user.domain.SimpleUser
import com.esofthead.mycollab.module.user.service.UserService
import com.esofthead.mycollab.schedule.email.format.{DateFieldFormat, FieldFormat, I18nFieldFormat, TagBuilder}
import com.esofthead.mycollab.schedule.email.project.BugRelayEmailNotificationAction
import com.esofthead.mycollab.schedule.email.{ItemFieldMapper, MailContext}
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.hp.gagawa.java.elements.{A, Img, Span}
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import scala.collection.mutable
import scala.util.control.Breaks._

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class BugRelayEmailNotificationActionImpl extends SendMailToFollowersAction[SimpleBug] with BugRelayEmailNotificationAction {

  private val LOG = LoggerFactory.getLogger(classOf[BugRelayEmailNotificationActionImpl])

  @Autowired var bugService: BugService = _

  @Autowired var projectService: ProjectService = _

  @Autowired var projectMemberService: ProjectMemberService = _

  @Autowired var projectNotificationService: ProjectNotificationSettingService = _

  private val mapper = new BugFieldNameMapper

  protected def buildExtraTemplateVariables(context: MailContext[SimpleBug]) {
    val listOfTitles: List[Map[String, String]] = List[Map[String, String]]()
    val currentProject: Map[String, String] = Map[String, String]()

    currentProject.+("displayName" -> bean.getProjectname)
    currentProject.+("webLink" -> ProjectLinkGenerator.generateProjectFullLink(siteUrl, bean.getProjectid))

    listOfTitles.+:(currentProject)

    val emailNotification: SimpleRelayEmailNotification = context.getEmailNotification
    val bugCode: Map[String, String] = Map[String, String]()
    val relatedProject: SimpleProject = projectService.findById(bean.getProjectid, emailNotification.getSaccountid)
    bugCode.+("displayName" -> "[" + relatedProject.getShortname + "-" + bean.getBugkey + "]")
    bugCode.+("webLink" -> ProjectLinkGenerator.generateBugPreviewFullLink(siteUrl, bean.getBugkey, bean
      .getProjectShortName))

    listOfTitles.+:(bugCode)
    val summary: String = bean.getSummary
    val summaryLink: String = ProjectLinkGenerator.generateBugPreviewFullLink(siteUrl, bean.getBugkey, bean.getProjectShortName)
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
      contentGenerator.putVariable("actionHeading", context.getMessage(BugI18nEnum.MAIL_CREATE_ITEM_HEADING, makeChangeUser))
    }
    else if (MonitorTypeConstants.UPDATE_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(BugI18nEnum.MAIL_UPDATE_ITEM_HEADING, makeChangeUser))
    }
    else if (MonitorTypeConstants.ADD_COMMENT_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(BugI18nEnum.MAIL_COMMENT_ITEM_HEADING, makeChangeUser))
    }
    contentGenerator.putVariable("titles", listOfTitles.toList)
    contentGenerator.putVariable("summary", summary)
    contentGenerator.putVariable("summaryLink", summaryLink)
  }

  protected def getBeanInContext(context: MailContext[SimpleBug]): SimpleBug = bugService.findById(context.getTypeid.toInt, context.getSaccountid)

  protected def getItemName: String = StringUtils.trim(bean.getSummary, 100)

  protected def getCreateSubject(context: MailContext[SimpleBug]): String = context.getMessage(BugI18nEnum.MAIL_CREATE_ITEM_SUBJECT, bean.getProjectname, context.getChangeByUserFullName, getItemName)

  protected def getUpdateSubject(context: MailContext[SimpleBug]): String = context.getMessage(BugI18nEnum.MAIL_UPDATE_ITEM_SUBJECT, bean.getProjectname, context.getChangeByUserFullName, getItemName)

  protected def getCommentSubject(context: MailContext[SimpleBug]): String = context.getMessage(BugI18nEnum.MAIL_COMMENT_ITEM_SUBJECT, bean.getProjectname, context.getChangeByUserFullName, getItemName)

  protected def getItemFieldMapper: ItemFieldMapper = mapper

  protected def getListNotifyUsersWithFilter(notification: ProjectRelayEmailNotification): List[SimpleUser] = {
    import scala.collection.JavaConverters._
    val notificationSettings: List[ProjectNotificationSetting] = projectNotificationService.findNotifications(notification.getProjectId, notification.getSaccountid).asScala.toList
    val activeUsers: List[SimpleUser] = projectMemberService.getActiveUsersInProject(notification.getProjectId, notification.getSaccountid).asScala.toList
    val inListUsers: mutable.Buffer[SimpleUser] = notification.getNotifyUsers.asScala.toBuffer
    if (notificationSettings != null) {
      for (notificationSetting <- notificationSettings) {
        if (NotificationType.None.name == notificationSetting.getLevel) {
          var i: Int = inListUsers.size - 1
          breakable {
            while (i >= 0) {
              val inUser: SimpleUser = inListUsers(i)
              if (inUser.getUsername != null && inUser.getUsername == notificationSetting.getUsername) {
                inListUsers remove i
                break
              }
              i = i - 1
            }
          }
        }
        else if (NotificationType.Minimal.name == notificationSetting.getLevel) {
          var isAlreadyInList: Boolean = false
          breakable {
            for (user <- inListUsers) {
              if (user.getUsername != null && user.getUsername == notificationSetting.getUsername) {
                isAlreadyInList = true
                break
              }
            }
          }

          if (!isAlreadyInList) {
            val bug: SimpleBug = bugService.findById(notification.getTypeid.toInt, notification.getSaccountid)
            if (bug.getAssignuser != null && (bug.getAssignuser == notificationSetting.getUsername)) {
              breakable {
                for (user <- activeUsers) {
                  if (user.getUsername != null && user.getUsername == notificationSetting.getUsername) {
                    inListUsers += user
                    break
                  }
                }
              }
            }
          }
        }
        else if (NotificationType.Full.name == notificationSetting.getLevel) {
          var isAlreadyInList: Boolean = false
          breakable {
            for (user <- inListUsers) {
              if ((user.getUsername != null) && (user.getUsername == notificationSetting.getUsername)) {
                isAlreadyInList = true
                break
              }
            }
          }

          if (!isAlreadyInList) {
            breakable {
              for (user <- activeUsers) {
                if ((user.getUsername != null) && (user.getUsername == notificationSetting.getUsername)) {
                  inListUsers += user
                  break
                }
              }
            }
          }
        }
      }
    }
    inListUsers.toList
  }

  class BugFieldNameMapper extends ItemFieldMapper {
    put(BugWithBLOBs.Field.summary, BugI18nEnum.FORM_SUMMARY, true)
    put(BugWithBLOBs.Field.environment, BugI18nEnum.FORM_ENVIRONMENT, true)
    put(BugWithBLOBs.Field.description, GenericI18Enum.FORM_DESCRIPTION, true)
    put(BugWithBLOBs.Field.assignuser, new AssigneeFieldFormat(BugWithBLOBs.Field.assignuser.name, GenericI18Enum.FORM_ASSIGNEE))
    put(BugWithBLOBs.Field.milestoneid, new MilestoneFieldFormat(BugWithBLOBs.Field.milestoneid.name, BugI18nEnum.FORM_PHASE))
    put(BugWithBLOBs.Field.status, new I18nFieldFormat(BugWithBLOBs.Field.status.name, BugI18nEnum.FORM_STATUS, classOf[OptionI18nEnum.BugStatus]))
    put(BugWithBLOBs.Field.resolution, new I18nFieldFormat(BugWithBLOBs.Field.resolution.name, BugI18nEnum.FORM_RESOLUTION, classOf[OptionI18nEnum.BugResolution]))
    put(BugWithBLOBs.Field.severity, new I18nFieldFormat(BugWithBLOBs.Field.severity.name, BugI18nEnum.FORM_SEVERITY, classOf[OptionI18nEnum.BugSeverity]))
    put(BugWithBLOBs.Field.priority, new I18nFieldFormat(BugWithBLOBs.Field.priority.name, BugI18nEnum.FORM_PRIORITY, classOf[OptionI18nEnum.BugPriority]))
    put(BugWithBLOBs.Field.duedate, new DateFieldFormat(BugWithBLOBs.Field.duedate.name, BugI18nEnum.FORM_DUE_DATE))
    put(BugWithBLOBs.Field.logby, new LogUserFieldFormat(BugWithBLOBs.Field.logby.name, BugI18nEnum.FORM_LOG_BY))
  }

  class MilestoneFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {

    def formatField(context: MailContext[_]): String = {
      val bug: SimpleBug = context.getWrappedBean.asInstanceOf[SimpleBug]
      if (bug.getMilestoneid == null || bug.getMilestoneName == null) {
        new Span().write
      }
      val milestoneIconLink: String = ProjectResources.getResourceLink(ProjectTypeConstants.MILESTONE)
      val img: Img = TagBuilder.newImg("icon", milestoneIconLink)
      val milestoneLink: String = ProjectLinkGenerator.generateMilestonePreviewFullLink(context.siteUrl, bug.getProjectid,
        bug.getMilestoneid)
      val link: A = TagBuilder.newA(milestoneLink, bug.getMilestoneName)
      TagBuilder.newLink(img, link).write
    }

    def formatField(context: MailContext[_], value: String): String = {
      if (org.apache.commons.lang3.StringUtils.isBlank(value)) {
        new Span().write
      }
      try {
        val milestoneId: Int = value.toInt
        val milestoneService: MilestoneService = ApplicationContextUtil.getSpringBean(classOf[MilestoneService])
        val milestone: SimpleMilestone = milestoneService.findById(milestoneId, context.getUser.getAccountId)
        if (milestone != null) {
          val milestoneIconLink: String = ProjectResources.getResourceLink(ProjectTypeConstants.MILESTONE)
          val img: Img = TagBuilder.newImg("icon", milestoneIconLink)
          val milestoneLink: String = ProjectLinkGenerator.generateMilestonePreviewFullLink(context.siteUrl, milestone
            .getProjectid, milestone.getId)
          val link: A = TagBuilder.newA(milestoneLink, milestone.getName)
          TagBuilder.newLink(img, link).write
        }
      }
      catch {
        case e: Exception => LOG.error("Error", e)
      }
      value
    }
  }

  class AssigneeFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {

    def formatField(context: MailContext[_]): String = {
      val bug: SimpleBug = context.getWrappedBean.asInstanceOf[SimpleBug]
      if (bug.getAssignuser != null) {
        val userAvatarLink: String = MailUtils.getAvatarLink(bug.getAssignUserAvatarId, 16)
        val img: Img = TagBuilder.newImg("avatar", userAvatarLink)
        val userLink: String = AccountLinkGenerator.generatePreviewFullUserLink(MailUtils.getSiteUrl(bug.getSaccountid), bug.getAssignuser)
        val link: A = TagBuilder.newA(userLink, bug.getAssignuserFullName)
        TagBuilder.newLink(img, link).write
      }
      else {
        new Span().write
      }
    }

    def formatField(context: MailContext[_], value: String): String = {
      if (org.apache.commons.lang3.StringUtils.isBlank(value)) {
        return new Span().write
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

  class LogUserFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {

    def formatField(context: MailContext[_]): String = {
      val bug: SimpleBug = context.getWrappedBean.asInstanceOf[SimpleBug]
      if (bug.getLogby != null) {
        val userAvatarLink: String = MailUtils.getAvatarLink(bug.getLoguserAvatarId, 16)
        val img: Img = TagBuilder.newImg("avatar", userAvatarLink)
        val userLink: String = AccountLinkGenerator.generatePreviewFullUserLink(MailUtils.getSiteUrl(bug.getSaccountid), bug.getLogby)
        val link: A = TagBuilder.newA(userLink, bug.getLoguserFullName)
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
