package com.esofthead.mycollab.module.billing.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.user.dao.UserAccountInvitationMapper;
import com.esofthead.mycollab.module.user.dao.UserAccountInvitationMapperExt;
import com.esofthead.mycollab.module.user.domain.SimpleUserAccountInvitation;
import com.esofthead.mycollab.schedule.ScheduleConfig;

@Service
public class SendInvitationUserCommandService {
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
					getSiteUrl(invitation.getSubdomain())
							+ "user/confirm_invite/"
							+ UrlEncodeDecoder.encode(invitation.getAccountid()
									+ "/" + invitation.getUsername()));
			templateGenerator.putVariable(
					"urlDeny",
					getSiteUrl(invitation.getSubdomain())
							+ "user/deny_invite/"
							+ UrlEncodeDecoder.encode(invitation.getAccountid()
									+ "/" + invitation.getUsername()));

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

	private String getSiteUrl(String subdomain) {
		if (ApplicationProperties.productionMode) {
			return String.format(ApplicationProperties
					.getString(ApplicationProperties.APP_URL), subdomain);
		} else {
			return ApplicationProperties
					.getString(ApplicationProperties.APP_URL);
		}
	}
}
