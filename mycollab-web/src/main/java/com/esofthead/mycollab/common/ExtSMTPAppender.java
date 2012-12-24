package com.esofthead.mycollab.common;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.log4j.net.SMTPAppender;

import com.esofthead.mycollab.common.service.ibatis.MailServiceImpl;

public class ExtSMTPAppender extends SMTPAppender {

	@Override
	protected Session createSession() {
		try {
			final Properties props = new Properties();
			props.load(MailServiceImpl.class.getClassLoader()
					.getResourceAsStream("resources.properties"));
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
			return session;
		} catch (Exception e) {
			return super.createSession();
		}
	}
}
