package com.esofthead.mycollab.module.mail.service.impl;

import java.io.IOException;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
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

	public static void main(String[] args) throws EmailException, IOException {
		HtmlEmail email = new HtmlEmail();
		email.setDebug(true);
		final Properties props = new Properties();
		props.load(MailServiceImpl.class.getClassLoader().getResourceAsStream(
				"resources.properties"));
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", props.get("mail.smtphost"));
		props.put("mail.smtp.port", props.get("mail.port"));

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(props
								.getProperty("mail.username"), props
								.getProperty("mail.password"));
					}
				});
		email.setMailSession(session);

		email.addTo("haiphucnguyen@gmail.com", "John Doe");
		email.setFrom("haiphucnguyen@gmail.com", "Me");
		email.setSubject("Test email with inline image");
		email.setHtmlMsg("<html>aaa</html>");
		email.send();
	}
}
