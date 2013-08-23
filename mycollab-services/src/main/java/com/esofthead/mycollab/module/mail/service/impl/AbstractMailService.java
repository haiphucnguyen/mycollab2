package com.esofthead.mycollab.module.mail.service.impl;

import java.util.List;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.module.mail.EmailAttachementSource;
import com.esofthead.mycollab.module.mail.Mailer;
import com.esofthead.mycollab.module.mail.service.IGenericMailService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

public abstract class AbstractMailService implements IGenericMailService {

	protected abstract Mailer getMailer();

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html,
			List<EmailAttachementSource> attachments) {

		getMailer().sendHTMLMail(fromEmail, fromName, toEmail, ccEmail,
				bccEmail, subject, html, attachments);

	}

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			List<SimpleUser> users, String subject, String html,
			List<EmailAttachementSource> attachment) {

		getMailer().sendHTMLMail(fromEmail, fromName, users, subject, html,
				attachment);
	}
}
