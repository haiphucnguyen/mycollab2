package com.esofthead.mycollab.module.mail;

import java.util.List;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

/**
 * Abstract mailer. Note: in MyCollab we now support send HTML content email
 * only.
 * 
 */
public interface IMailer {
	/**
	 * 
	 * @param fromEmail
	 * @param fromName
	 * @param toEmail
	 * @param ccEmail
	 * @param bccEmail
	 * @param subject
	 * @param html
	 */
	void sendHTMLMail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html);

	/**
	 * 
	 * @param fromEmail
	 * @param fromName
	 * @param toEmail
	 * @param ccEmail
	 * @param bccEmail
	 * @param subject
	 * @param html
	 * @param attachments
	 */
	void sendHTMLMail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html,
			List<EmailAttachementSource> attachments);

	/**
	 * 
	 * @param fromEmail
	 * @param fromName
	 * @param users
	 * @param subject
	 * @param html
	 * @param attachment
	 */
	void sendHTMLMail(String fromEmail, String fromName,
			List<SimpleUser> users, String subject, String html,
			List<EmailAttachementSource> attachment);
}
