package com.mycollab.module.project.esb

import java.util
import java.util.{Date, Locale}

import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.common.i18n.MailI18nEnum
import com.mycollab.configuration.{IDeploymentMode, SiteConfiguration}
import com.mycollab.core.utils.{DateTimeUtils, RandomPasswordGenerator}
import com.mycollab.html.LinkUtils
import com.mycollab.i18n.LocalizationHelper
import com.mycollab.module.billing.RegisterStatusConstants
import com.mycollab.module.esb.GenericCommand
import com.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.mycollab.module.project.domain.ProjectMember
import com.mycollab.module.project.i18n.ProjectMemberI18nEnum
import com.mycollab.module.project.service.{ProjectMemberService, ProjectService}
import com.mycollab.module.project.{ProjectLinkGenerator, ProjectMemberStatusConstants}
import com.mycollab.module.user.domain.User
import com.mycollab.module.user.service.{RoleService, UserService}
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 1.0.0
  */
@Component class InviteProjectMembersCommand extends GenericCommand {
  val LOG = LoggerFactory.getLogger(classOf[InviteProjectMembersCommand])
  @Autowired private val userService: UserService = null
  @Autowired private val roleService: RoleService = null
  @Autowired private val deploymentMode: IDeploymentMode = null
  @Autowired private val extMailService: ExtMailService = null
  @Autowired private val projectService: ProjectService = null
  @Autowired private val projectMemberService: ProjectMemberService = null
  @Autowired private val contentGenerator: IContentGenerator = null

  @AllowConcurrentEvents
  @Subscribe
  def inviteUsers(event: InviteProjectMembersEvent): Unit = {
    val project = projectService.findById(event.projectId, event.sAccountId)
    val user = userService.findUserInAccount(event.inviteUser, event.sAccountId)
    val billingAccount = projectService.getAccountInfoOfProject(event.projectId)

    contentGenerator.putVariable("inviteUser", user.getDisplayName)
    contentGenerator.putVariable("inviteMessage", event.inviteMessage)
    contentGenerator.putVariable("project", project)
    contentGenerator.putVariable("password", null)
    contentGenerator.putVariable("logoPath", LinkUtils.accountLogoPath(billingAccount.getId, billingAccount.getLogopath))

    for (inviteeEmail <- event.emails) {
      val invitee = userService.findUserInAccount(inviteeEmail, event.sAccountId)
      contentGenerator.putVariable("inviteeEmail", inviteeEmail)
      if (invitee != null) {
        if (RegisterStatusConstants.ACTIVE != invitee.getRegisterstatus) {
          userService.updateUserAccountStatus(inviteeEmail, event.sAccountId, RegisterStatusConstants.ACTIVE)
        }
      } else {
        val systemGuestRoleId = roleService.getDefaultRoleId(event.sAccountId)
        if (systemGuestRoleId == null) {
          LOG.error("Can not find the guess role of account ", event.sAccountId)
        }

        val newUser = new User
        newUser.setEmail(inviteeEmail)
        val password = RandomPasswordGenerator.generateRandomPassword()
        contentGenerator.putVariable("password", password)
        newUser.setPassword(password)
        userService.saveUserAccount(newUser, systemGuestRoleId, billingAccount.getSubdomain, event.sAccountId, event.inviteUser, false)
      }
      val projectMember = projectMemberService.findMemberByUsername(inviteeEmail, event.projectId, event.sAccountId)
      if (projectMember != null) {
        if (ProjectMemberStatusConstants.ACTIVE != projectMember.getStatus) {
          projectMember.setStatus(ProjectMemberStatusConstants.NOT_ACCESS_YET)
        } else {
          return
        }
        if (event.projectRoleId == null || event.projectRoleId < 0) {
          projectMember.setIsadmin(true)
          projectMember.setProjectroleid(null)
        } else {
          projectMember.setIsadmin(false)
          projectMember.setProjectroleid(event.projectRoleId)
        }
        projectMemberService.updateWithSession(projectMember, "")
      } else {
        val member = new ProjectMember
        member.setProjectid(event.projectId)
        member.setUsername(inviteeEmail)
        member.setJoindate(new Date)
        member.setSaccountid(event.sAccountId)
        member.setBillingrate(project.getDefaultbillingrate)
        member.setOvertimebillingrate(project.getDefaultovertimebillingrate)
        member.setStatus(ProjectMemberStatusConstants.NOT_ACCESS_YET)
        if (event.projectRoleId == null || event.projectRoleId < 0) {
          member.setIsadmin(true)
          member.setProjectroleid(null)
        }
        else {
          member.setIsadmin(false)
          member.setProjectroleid(event.projectRoleId)
        }
        projectMemberService.saveWithSession(member, "")
      }
      contentGenerator.putVariable("copyRight", LocalizationHelper.getMessage(Locale.US, MailI18nEnum.Copyright,
        DateTimeUtils.getCurrentYear))
      contentGenerator.putVariable("urlAccept", ProjectLinkGenerator.generateProjectFullLink(deploymentMode.getSiteUrl(billingAccount.getSubdomain),
        event.projectId))
      val subject = LocalizationHelper.getMessage(Locale.US, ProjectMemberI18nEnum.MAIL_INVITE_USERS_SUBJECT,
        project.getName, SiteConfiguration.getDefaultSiteName)
      val content = contentGenerator.parseFile("mailMemberInvitationNotifier.ftl", Locale.US)
      val toUser = util.Arrays.asList(new MailRecipientField(inviteeEmail, inviteeEmail))
      extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName, toUser, subject, content)
    }
  }
}