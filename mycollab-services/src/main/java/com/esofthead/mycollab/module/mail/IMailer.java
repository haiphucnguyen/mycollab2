package com.esofthead.mycollab.module.mail;

import java.util.List;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

/**
 * 
 * @author haiphucnguyen
 *
 */
public interface IMailer {
	void sendHTMLMail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html);

	void sendHTMLMail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html,
			List<EmailAttachementSource> attachments);

	void sendHTMLMail(String fromEmail, String fromName,
			List<SimpleUser> users, String subject, String html,
			List<EmailAttachementSource> attachment);
}
