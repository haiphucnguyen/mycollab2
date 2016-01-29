package com.esofthead.mycollab.module.project.esb.impl

import com.esofthead.mycollab.common.domain.MailRecipientField
import com.esofthead.mycollab.configuration.SiteConfiguration
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.esofthead.mycollab.module.project.esb.NewProjectMemberJoinEvent
import com.esofthead.mycollab.module.project.service.ProjectMemberService
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import scala.collection.mutable.ListBuffer

/**
  * @author MyCollab Ltd
  * @since 5.2.6
  */
@Component class NewProjectMemberJoinCommand extends GenericCommand {
  @Autowired private val projectMemberService: ProjectMemberService = null
  @Autowired private val extMailService: ExtMailService = null
  @Autowired private val contentGenerator: IContentGenerator = null

  @AllowConcurrentEvents
  @Subscribe
  def newMemberJoinHandler(event: NewProjectMemberJoinEvent): Unit = {
    val projectId = event.projectId
    val sAccountId = event.sAccountId
    val newMember = projectMemberService.findMemberByUsername(event.username, projectId, sAccountId)
    import scala.collection.JavaConverters._
    val membersInProjects = projectMemberService.getActiveUsersInProject(projectId, sAccountId).asScala.toList
    contentGenerator.putVariable("newMember", newMember)
    val recipients = ListBuffer[MailRecipientField]()
    membersInProjects.foreach(user => recipients.append(new MailRecipientField(user.getUsername, user.getDisplayName)))
    extMailService.sendHTMLMail(SiteConfiguration.getNoReplyEmail, SiteConfiguration.getDefaultSiteName, recipients.asJava,
      null, null, String.format("%s has joined on project %s", newMember.getDisplayName, newMember.getProjectName),
      contentGenerator.parseFile("templates/email/project/newMemberJoinProjectNotifier.mt", SiteConfiguration.getDefaultLocale), null)
    if (event.isNewAccountCreated) {

    }
  }
}
