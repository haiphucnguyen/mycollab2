package com.esofthead.mycollab.module.project.esb.impl

import java.util
import java.util.{Date, Locale}

import com.esofthead.mycollab.common.domain.MailRecipientField
import com.esofthead.mycollab.configuration.SiteConfiguration
import com.esofthead.mycollab.i18n.LocalizationHelper
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.esofthead.mycollab.module.project.ProjectLinkGenerator
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember
import com.esofthead.mycollab.module.project.esb.InviteProjectMembersEvent
import com.esofthead.mycollab.module.project.i18n.ProjectMemberI18nEnum
import com.esofthead.mycollab.module.project.service.ProjectService
import com.esofthead.mycollab.module.user.service.UserService
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 1.0.0
  */
@Component class InviteProjectMembersCommand extends GenericCommand {
  @Autowired private val userService: UserService = null
  @Autowired private val extMailService: ExtMailService = null
  @Autowired private val projectService: ProjectService = null
  @Autowired private val contentGenerator: IContentGenerator = null

  @AllowConcurrentEvents
  @Subscribe
  def inviteUsers(event: InviteProjectMembersEvent): Unit = {
    val project = projectService.findById(event.projectId, event.sAccountId)
    val user = userService.findUserByUserNameInAccount(event.inviteUser, event.sAccountId)
    val member = new SimpleProjectMember
    member.setProjectName(project.getName)
    contentGenerator.putVariable("member", member)
    contentGenerator.putVariable("inviteUser", user.getDisplayName)
    contentGenerator.putVariable("inviteMessage", event.inviteMessage)
    val subDomain = projectService.getSubdomainOfProject(event.projectId)
    val date: Date = new Date
    for (inviteeEmail <- event.emails) {
      contentGenerator.putVariable("urlAccept", ProjectLinkGenerator.generateProjectFullLink(SiteConfiguration.getSiteUrl(subDomain), event.projectId))
      val subject = contentGenerator.parseString(LocalizationHelper.getMessage(Locale.US,
        ProjectMemberI18nEnum.MAIL_INVITE_USERS_SUBJECT, member.getProjectName, SiteConfiguration.getDefaultSiteName))
      val content = contentGenerator.parseFile("templates/email/project/memberInvitationNotifier.mt",
        Locale.US)
      val toUser = util.Arrays.asList(new MailRecipientField(inviteeEmail, inviteeEmail))
      extMailService.sendHTMLMail(SiteConfiguration.getNoReplyEmail, SiteConfiguration.getDefaultSiteName,
        toUser, null, null, subject, content, null)
    }
  }
}