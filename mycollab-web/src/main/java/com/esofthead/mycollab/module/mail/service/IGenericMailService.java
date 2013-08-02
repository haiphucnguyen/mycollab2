package com.esofthead.mycollab.module.mail.service;

import java.util.List;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.module.mail.EmailAttachementSource;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

public interface IGenericMailService {
	void sendHTMLMail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html,
			List<EmailAttachementSource> attachments);

	void sendHTMLMail(String fromEmail, String fromName,
			List<SimpleUser> users, String subject, String html,
			List<EmailAttachementSource> attachment);
}
