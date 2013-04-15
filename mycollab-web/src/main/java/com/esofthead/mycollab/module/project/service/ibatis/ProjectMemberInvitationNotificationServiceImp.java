package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberInvitiationNotificationService;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;

@Service
public class ProjectMemberInvitationNotificationServiceImp implements
		ProjectMemberInvitiationNotificationService {

	@Autowired
	private ProjectMemberService projectMemberService;

	@Autowired
	protected ExtMailService extMailService;

	@Override
	public void sendNotificationForCreateAction(
			SimpleRelayEmailNotification notification) {
		int projectMemberId = notification.getTypeid();

		SimpleProjectMember member = projectMemberService
				.findMemberById(projectMemberId);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"MyCollab has invited you to join the team for project \" $member.projectName\"",
				"templates/email/project/memberInvitation/memberInvitationNotifier.mt");
		templateGenerator.putVariable("member", member);
		templateGenerator.putVariable(
				"urlAccept",
				ApplicationProperties
						.getProperty(ApplicationProperties.APP_URL)
						+ "?url="
						+ "project/member/invitation/confirm_invite"
						+ projectMemberId);
		templateGenerator.putVariable(
				"urlDeny",
				ApplicationProperties
						.getProperty(ApplicationProperties.APP_URL)
						+ "?url="
						+ "project/member/invitation/deny_invite"
						+ projectMemberId);

		System.out.println("mail: " + member.getUsername() + " name: "
				+ member.getMemberFullName() + " changeuse: "
				+ notification.getChangeByUserFullName());
		
		extMailService.sendHTMLMail("mail@esofthead.com", notification
				.getChangeByUserFullName(), Arrays
				.asList(new MailRecipientField(member.getUsername(), member
						.getMemberFullName())), null, null, templateGenerator
				.generateSubjectContent(), templateGenerator
				.generateBodyContent(), null);
	}

	@Override
	public void sendNotificationForUpdateAction(
			SimpleRelayEmailNotification notification) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendNotificationForCommentAction(
			SimpleRelayEmailNotification notification) {
		// TODO Auto-generated method stub

	}

}
