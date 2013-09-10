package com.esofthead.mycollab.schedule.email.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.RelayEmailWithBLOBs;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.schedule.email.user.SendingRecoveryPasswordEmailAction;

@Service
public class SendingRecoveryPasswordEmailActionImpl implements
		SendingRecoveryPasswordEmailAction {

	private static Logger log = LoggerFactory
			.getLogger(SendingRecoveryPasswordEmailActionImpl.class);

	@Autowired
	protected ExtMailService extMailService;

	@Autowired
	protected UserService userService;

	@Override
	public void sendEmail(RelayEmailWithBLOBs relayEmail) {
		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[MyCollab] - User recovery password",
				"templates/email/user/userRecoveryPasswordNotifier.mt");

		String username = relayEmail.getRecipients();
		if (username != null) {
			log.debug("Send recovery password email to user {}", username);
			User user = userService.findUserByUserName(username);
			String subdomain = "";
			String recoveryPasswordURL = SiteConfiguration
					.getSiteUrl(subdomain)
					+ "user/recoverypassword/"
					+ UrlEncodeDecoder.encode(username);

			templateGenerator.putVariable("username", user.getUsername());
			templateGenerator.putVariable("urlRecoveryPassword",
					recoveryPasswordURL);

			MailRecipientField recipient = new MailRecipientField(
					user.getEmail(), user.getUsername());
			List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
			lst.add(recipient);
			extMailService.sendHTMLMail("mail@esofthead.com", "MyCollab", lst,
					null, null, templateGenerator.generateSubjectContent(),
					templateGenerator.generateBodyContent(), null);
		}
	}
}
