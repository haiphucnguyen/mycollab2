package com.esofthead.mycollab.schedule.email.project;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.project.dao.ProjectMemberMapper;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.schedule.email.DefaultSendingRelayEmailNotificationAction;

@Service
public class ProjectMemberInviteNotificationActionImpl extends
		DefaultSendingRelayEmailNotificationAction implements
		ProjectMemberInviteNotificationAction {

	@Autowired
	private ProjectMemberService projectMemberService;

	@Autowired
	private ProjectMemberMapper projectMemberMapper;

	@Autowired
	private ProjectService projectService;

	@Autowired
	protected ExtMailService extMailService;

	@Autowired
	private UserService userService;

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		int project_member_id = emailNotification.getTypeid();
		SimpleProjectMember member = projectMemberService.findById(
				project_member_id, emailNotification.getSaccountid());
		String subdomain = projectService.getSubdomainOfProject(member
				.getProjectid());

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"$inviteUser has invited you to join the team for project \" $member.projectName\"",
				"templates/email/project/memberInvitation/memberInvitationNotifier.mt");
		templateGenerator.putVariable("member", member);
		templateGenerator.putVariable("inviteUser", member.getMemberFullName());
		templateGenerator.putVariable(
				"urlAccept",
				SiteConfiguration.getSiteUrl(subdomain)
						+ "project/member/invitation/confirm_invite/"
						+ UrlEncodeDecoder.encode(member.getsAccountId() + "/"
								+ member.getId()));
		templateGenerator.putVariable(
				"urlDeny",
				SiteConfiguration.getSiteUrl(subdomain)
						+ "project/member/invitation/deny_invite/"
						+ UrlEncodeDecoder.encode(member.getsAccountId() + "/"
								+ member.getId()));

		extMailService.sendHTMLMail("mail@esofthead.com", "No-reply", Arrays
				.asList(new MailRecipientField(member.getEmail(), member
						.getMemberFullName())), null, null, templateGenerator
				.generateSubjectContent(), templateGenerator
				.generateBodyContent(), null);

		// Send email and change register status of user to
		// RegisterStatusConstants.SENT_VERIFICATION_EMAIL
		member.setStatus(RegisterStatusConstants.SENT_VERIFICATION_EMAIL);
		projectMemberService.updateWithSession(member,
				emailNotification.getChangeByUserFullName());
		return templateGenerator;
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		// do nothing
		return null;
	}

	@Override
	protected TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification) {
		// do nothing
		return null;
	}

}
