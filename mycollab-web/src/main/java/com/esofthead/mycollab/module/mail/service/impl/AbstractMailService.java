package com.esofthead.mycollab.module.mail.service.impl;

import java.util.List;

import org.apache.commons.mail.EmailException;

import com.esofthead.mycollab.module.mail.EmailAttachementSource;
import com.esofthead.mycollab.module.mail.Mailer;
import com.esofthead.mycollab.module.mail.service.IGenericMailService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

public abstract class AbstractMailService implements IGenericMailService {

	protected abstract Mailer getMailer();

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			String[] toEmail, String[] toName, String subject, String html,
			List<EmailAttachementSource> attachment) {
		try {
			getMailer().sendHTMLMail(fromEmail, fromName, toEmail, toName,
					subject, html, attachment);
		} catch (EmailException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			List<SimpleUser> users, String subject, String html,
			List<EmailAttachementSource> attachment) {
		String[] toEmails = new String[users.size()];
		String[] toNames = new String[users.size()];

		for (int i = 0; i < users.size(); i++) {
			toEmails[i] = users.get(i).getEmail();
			toNames[i] = users.get(i).getDisplayName();
		}

		this.sendHTMLMail(fromEmail, fromName, toEmails, toNames, subject, html, attachment);
	}
}
