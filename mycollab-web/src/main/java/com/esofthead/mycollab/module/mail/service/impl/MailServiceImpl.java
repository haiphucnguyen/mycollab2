package com.esofthead.mycollab.module.mail.service.impl;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.module.mail.Mailer;
import com.esofthead.mycollab.module.mail.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	private static Mailer gmailer;

	static {
		final Properties props = new Properties();
		try {
			props.load(MailServiceImpl.class.getClassLoader()
					.getResourceAsStream("resources.properties"));
			gmailer = new Mailer(props.getProperty("mail.smtphost"),
					props.getProperty("mail.username"),
					props.getProperty("mail.password"), true);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
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
}
