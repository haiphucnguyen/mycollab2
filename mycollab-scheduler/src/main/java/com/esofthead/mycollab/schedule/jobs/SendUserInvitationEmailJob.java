package com.esofthead.mycollab.schedule.jobs;

import java.util.Arrays;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.QuartzJobBean;
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

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class SendUserInvitationEmailJob extends QuartzJobBean {
	private static Logger log = LoggerFactory
			.getLogger(SendUserInvitationEmailJob.class);

	@Autowired
	private UserAccountInvitationMapper userAccountInvitationMapper;

	@Autowired
	private UserAccountInvitationMapperExt userAccountInvitationMapperExt;

	@Autowired
	private ExtMailService extMailService;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		List<SimpleUserAccountInvitation> invitations = userAccountInvitationMapperExt
				.findAccountInvitations(RegisterStatusConstants.VERIFICATING);

		for (SimpleUserAccountInvitation invitation : invitations) {
			log.debug("Send invitation email to user {} of subdomain {}",
					invitation.getUsername(), invitation.getSubdomain());

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

			String inviterName = invitation.getInviterFullName();
			String inviterMail = invitation.getInviteuser();
			String subdomain = invitation.getSubdomain();
			templateGenerator.putVariable(
					"urlDeny",
					SiteConfiguration.getSiteUrl(invitation.getSubdomain())
							+ "user/deny_invite/"
							+ UrlEncodeDecoder.encode(invitation.getAccountid()
									+ "/" + invitation.getUsername() + "/"
									+ inviterName + "/" + inviterMail + "/"
									+ subdomain));
			String userName = (invitation.getUsername() != null) ? invitation
					.getUsername() : "there";
			templateGenerator.putVariable("userName", userName);
			templateGenerator.putVariable("inviterName", inviterName);
			extMailService.sendHTMLMail("noreply@mycollab.com",
					"noreply@mycollab.com", Arrays
							.asList(new MailRecipientField(invitation
									.getUsername(), invitation.getUsername())),
					null, null, templateGenerator.generateSubjectContent(),
					templateGenerator.generateBodyContent(), null);

			// Send email and change register status of user to
			// RegisterStatusConstants.SENT_VERIFICATION_EMAIL
			invitation
					.setInvitationstatus(RegisterStatusConstants.SENT_VERIFICATION_EMAIL);
			userAccountInvitationMapper.updateByPrimaryKeySelective(invitation);
		}

	}
}
