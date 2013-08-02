package com.esofthead.mycollab.module.mail.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;

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
		try {
			getMailer().sendHTMLMail(fromEmail, fromName, toEmail, ccEmail,
					bccEmail, subject, html, attachments);
		} catch (EmailException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			List<SimpleUser> users, String subject, String html,
			List<EmailAttachementSource> attachment) {

		List<MailRecipientField> lstRecipient = new ArrayList<MailRecipientField>();
		for (int i = 0; i < users.size(); i++) {
			String mail = users.get(i).getEmail();
			String mailName = (users.get(i).getDisplayName() == null || users
					.get(i).getDisplayName().equals("")) ? mail : users.get(i)
					.getDisplayName();
			lstRecipient.add(new MailRecipientField(mail, mailName));
		}

		this.sendHTMLMail(fromEmail, fromName, lstRecipient, null, null,
				subject, html, attachment);
	}
}
