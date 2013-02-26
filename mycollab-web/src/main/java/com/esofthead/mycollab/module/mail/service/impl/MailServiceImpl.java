package com.esofthead.mycollab.module.mail.service.impl;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.module.mail.Mailer;
import com.esofthead.mycollab.module.mail.service.MailService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

@Service
public class MailServiceImpl implements MailService {

	private static Mailer gmailer;

	static {

		gmailer = new Mailer(
				ApplicationProperties.getProperty("mail.smtphost"),
				ApplicationProperties.getProperty("mail.username"),
				ApplicationProperties.getProperty("mail.password"), true);
	}

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			String[] toEmail, String[] toName, String subject, String html) {
		try {
			gmailer.sendHTMLMail(fromEmail, fromName, toEmail, toName, subject,
					html);
		} catch (EmailException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			List<SimpleUser> users, String subject, String html) {
		String[] toEmails = new String[users.size()];
		String[] toNames = new String[users.size()];

		for (int i = 0; i < users.size(); i++) {
			toEmails[i] = users.get(i).getEmail();
			toNames[i] = users.get(i).getDisplayName();
		}

		this.sendHTMLMail(fromEmail, fromName, toEmails, toNames, subject, html);
	}
}
