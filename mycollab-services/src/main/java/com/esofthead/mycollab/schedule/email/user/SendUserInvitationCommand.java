package com.esofthead.mycollab.schedule.email.user;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.user.dao.UserAccountInvitationMapper;
import com.esofthead.mycollab.module.user.dao.UserAccountInvitationMapperExt;
import com.esofthead.mycollab.module.user.domain.SimpleUserAccountInvitation;
import com.esofthead.mycollab.schedule.email.ScheduleConfig;

@Component
public class SendUserInvitationCommand {
	@Autowired
	private UserAccountInvitationMapper userAccountInvitationMapper;

	@Autowired
	private UserAccountInvitationMapperExt userAccountInvitationMapperExt;

	@Autowired
	private ExtMailService extMailService;

	@Scheduled(fixedDelay = ScheduleConfig.RUN_EMAIL_NOTIFICATION_INTERVAL)
	public void runNotification() {

		List<SimpleUserAccountInvitation> invitations = userAccountInvitationMapperExt
				.findAccountInvitations(RegisterStatusConstants.VERIFICATING);

		for (SimpleUserAccountInvitation invitation : invitations) {
			TemplateGenerator templateGenerator = new TemplateGenerator(
					"You are invited to join the mycollab site ",
					"templates/email/user/userInvitationNotifier.mt");
			templateGenerator.putVariable("invitation", invitation);

			templateGenerator.putVariable(
					"urlAccept",
					SiteConfiguration.getSiteUrl(invitation.getSubdomain())
							+ "user/confirm_invite/"
							+ UrlEncodeDecoder.encode(invitation.getAccountid()
									+ "/" + invitation.getUsername() + "/"
									+ invitation.getSubdomain()));
			templateGenerator.putVariable(
					"urlDeny",
					SiteConfiguration.getSiteUrl(invitation.getSubdomain())
							+ "user/deny_invite/"
							+ UrlEncodeDecoder.encode(invitation.getAccountid()
									+ "/" + invitation.getUsername()));
			String userName = (invitation.getUsername() != null) ? invitation
					.getUsername() : "there";
			templateGenerator.putVariable("userName", userName);
			extMailService.sendHTMLMail("mail@esofthead.com", "No-reply",
					Arrays.asList(new MailRecipientField(invitation
							.getUsername(), invitation.getUsername())), null,
					null, templateGenerator.generateSubjectContent(),
					templateGenerator.generateBodyContent(), null);

			// Send email and change register status of user to
			// RegisterStatusConstants.SENT_VERIFICATION_EMAIL
			invitation
					.setInvitationstatus(RegisterStatusConstants.SENT_VERIFICATION_EMAIL);
			userAccountInvitationMapper.updateByPrimaryKeySelective(invitation);
		}
	}
}
