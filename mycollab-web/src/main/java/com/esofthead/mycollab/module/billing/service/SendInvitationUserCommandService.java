package com.esofthead.mycollab.module.billing.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.esofthead.mycollab.module.user.domain.UserAccountInvitation;
import com.esofthead.mycollab.module.user.domain.UserAccountInvitationExample;
import com.esofthead.mycollab.schedule.ScheduleConfig;

@Service
public class SendInvitationUserCommandService {
	@Autowired
	private UserAccountInvitationMapper userAccountInvitationMapper;

	@Autowired
	private ExtMailService extMailService;

	@Scheduled(fixedDelay = ScheduleConfig.RUN_EMAIL_NOTIFICATION_INTERVAL)
	public void runNotification() {
		UserAccountInvitationExample invitationEx = new UserAccountInvitationExample();
		invitationEx.createCriteria().andInvitationstatusEqualTo(
				RegisterStatusConstants.VERIFICATING);

		List<UserAccountInvitation> invitations = userAccountInvitationMapper
				.selectByExample(invitationEx);

		for (UserAccountInvitation invitation : invitations) {
			TemplateGenerator templateGenerator = new TemplateGenerator(
					"You are invited to join the mycollab site ",
					"templates/email/user/userInvitationNotifier.mt");
			templateGenerator.putVariable("invitation", invitation);

			templateGenerator.putVariable(
					"urlAccept",
					ApplicationProperties
							.getProperty(ApplicationProperties.APP_URL)
							+ "user/confirm_invite/"
							+ UrlEncodeDecoder.encode(invitation.getAccountid()
									+ "/" + invitation.getUsername()));
			templateGenerator.putVariable(
					"urlDeny",
					ApplicationProperties
							.getProperty(ApplicationProperties.APP_URL)
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
}
