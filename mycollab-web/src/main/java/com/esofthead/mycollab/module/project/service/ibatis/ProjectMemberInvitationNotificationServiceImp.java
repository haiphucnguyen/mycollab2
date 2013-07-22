package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.ProjectLinkBuilder;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.schedule.email.command.MessageRelayEmailNotificationActionImpl;
import com.esofthead.mycollab.web.AppContext;

@Service
public class ProjectMemberInvitationNotificationServiceImp implements
		MessageRelayEmailNotificationActionImpl {

	@Autowired
	private ProjectMemberService projectMemberService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	protected ExtMailService extMailService;

	@Autowired
	private UserService userService;

	@Override
	public void sendNotificationForCreateAction(
			SimpleRelayEmailNotification notification) {
		int projectMemberId = notification.getTypeid();

		SimpleProjectMember member = projectMemberService
				.findMemberById(projectMemberId);

		if (member != null) {
			String subdomain = projectService.getSubdomainOfProject(member
					.getProjectid());

			TemplateGenerator templateGenerator = new TemplateGenerator(
					"$inviteUser has invited you to join the team for project \" $member.projectName\"",
					"templates/email/project/memberInvitation/memberInvitationNotifier.mt");
			templateGenerator.putVariable("member", member);
			templateGenerator.putVariable("inviteUser",
					notification.getChangeByUserFullName());
			templateGenerator.putVariable(
					"urlAccept",
					TemplateGenerator.getSiteUrl(subdomain)
							+ "project/member/invitation/confirm_invite/"
							+ UrlEncodeDecoder.encode(notification
									.getSaccountid()
									+ "/"
									+ notification.getChangeby()
									+ "/"
									+ projectMemberId));
			templateGenerator.putVariable(
					"urlDeny",
					TemplateGenerator.getSiteUrl(subdomain)
							+ "project/member/invitation/deny_invite/"
							+ UrlEncodeDecoder.encode(notification
									.getSaccountid()
									+ "/"
									+ notification.getChangeby()
									+ "/"
									+ projectMemberId));

			extMailService.sendHTMLMail("mail@esofthead.com", "No-reply",
					Arrays.asList(new MailRecipientField(member.getEmail(),
							member.getMemberFullName())), null, null,
					templateGenerator.generateSubjectContent(),
					templateGenerator.generateBodyContent(), null);
		}
	}

	@Override
	public void sendNotificationForUpdateAction(
			SimpleRelayEmailNotification notification) {
		int projectId = notification.getTypeid();

		SimpleProject project = projectService.findProjectById(projectId);

		SimpleUser user = userService.findUserByUserNameInAccount(
				notification.getChangeby(), AppContext.getAccountId());
		if (project != null && user != null) {
			TemplateGenerator templateGenerator = new TemplateGenerator(
					"User \"$notification.changecomment\" has accepted for the invitation of project \"$project.name\"",
					"templates/email/project/memberInvitation/memberAcceptInvitationNotifier.mt");

			ProjectLinkBuilder.MailLinkGenerator linkGenerator = new ProjectLinkBuilder.MailLinkGenerator(
					project.getId());
			templateGenerator.putVariable("project", project);
			templateGenerator.putVariable("notification", notification);
			templateGenerator.putVariable("projectUrl",
					linkGenerator.generateProjectFullLink());

			extMailService.sendHTMLMail("mail@esofthead.com", "No-reply",
					Arrays.asList(new MailRecipientField(user.getEmail(), user
							.getDisplayName())), null, null, templateGenerator
							.generateSubjectContent(), templateGenerator
							.generateBodyContent(), null);
		}

	}

	@Override
	public void sendNotificationForCommentAction(
			SimpleRelayEmailNotification notification) {
		int projectId = notification.getTypeid();

		SimpleProject project = projectService.findProjectById(projectId);

		SimpleUser user = userService.findUserByUserNameInAccount(
				notification.getChangeby(), AppContext.getAccountId());

		if (project != null && user != null) {
			TemplateGenerator templateGenerator = new TemplateGenerator(
					"User \"$notification.changecomment\" has denied for the invitation of project \"$project.name\"",
					"templates/email/project/memberInvitation/memberDenyInvitationNotifier.mt");

			ProjectLinkBuilder.MailLinkGenerator linkGenerator = new ProjectLinkBuilder.MailLinkGenerator(
					project.getId());

			templateGenerator.putVariable("project", project);
			templateGenerator.putVariable("notification", notification);
			templateGenerator.putVariable("projectUrl",
					linkGenerator.generateProjectFullLink());

			extMailService.sendHTMLMail("mail@esofthead.com", "No-reply",
					Arrays.asList(new MailRecipientField(user.getEmail(), user
							.getDisplayName())), null, null, templateGenerator
							.generateSubjectContent(), templateGenerator
							.generateBodyContent(), null);
		}

	}

}
