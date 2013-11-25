package com.esofthead.mycollab.module.mail;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

/**
 * Dummy a mailer in case email configuration not properly set.
 */
public class NullMailer implements IMailer {

	private static Logger log = LoggerFactory.getLogger(NullMailer.class);

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html) {
		log.info("You did not configure email. So Email feature is disable and MyCollab can not send any notification via email.");
	}

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html,
			List<EmailAttachementSource> attachments) {
		log.info("You did not configure email. So Email feature is disable and MyCollab can not send any notification via email.");

	}

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			List<SimpleUser> users, String subject, String html,
			List<EmailAttachementSource> attachment) {
		log.info("You did not configure email. So Email feature is disable and MyCollab can not send any notification via email.");

	}

}
