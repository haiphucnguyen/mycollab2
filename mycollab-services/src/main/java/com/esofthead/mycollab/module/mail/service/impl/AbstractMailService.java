package com.esofthead.mycollab.module.mail.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.module.mail.EmailAttachementSource;
import com.esofthead.mycollab.module.mail.Mailer;
import com.esofthead.mycollab.module.mail.service.IGenericMailService;

public abstract class AbstractMailService implements IGenericMailService {

	private static Logger log = LoggerFactory
			.getLogger(AbstractMailService.class);

	protected abstract Mailer getMailer();

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html,
			List<EmailAttachementSource> attachments) {

		getMailer().sendHTMLMail(fromEmail, fromName, toEmail, ccEmail,
				bccEmail, subject, html, attachments);

	}
}
