package com.esofthead.mycollab.module.mail.service.gmail;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.esofthead.mycollab.module.mail.service.MailConfig;
import com.esofthead.mycollab.module.mail.service.Util;

public class GmailManager {

	public static final void sendMail(List<String> recipients, String subject,
			String html, List<String> attachments) throws Exception {
		final String host = "smtp.gmail.com";
		final int port = 587;
		final String userName = MailConfig.getProperty(MailConfig.USER_NAME);
		final String password = MailConfig.getProperty(MailConfig.PASSWORD);

		final Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.gmail", host);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", String.valueOf(port));
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.password", password);

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(userName, userName);
					}
				});

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(MailConfig
				.getProperty(MailConfig.MAIL_FROM)));
		message.setSubject(subject);

		javax.mail.internet.InternetAddress[] toRecipients = new javax.mail.internet.InternetAddress[recipients
				.size()];
		for (int i = 0; i < recipients.size(); i++) {
			toRecipients[i] = new javax.mail.internet.InternetAddress(
					recipients.get(i));
		}
		message.setRecipients(javax.mail.Message.RecipientType.TO, toRecipients);

		Multipart multipart = new MimeMultipart();

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(html, "text/html; charset=utf-8");
		multipart.addBodyPart(messageBodyPart);

		if (null != attachments && attachments.size() > 0) {
			for (String filePath : attachments) {
				File file = new File(filePath);

				messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(filePath);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(file.getName());

				multipart.addBodyPart(messageBodyPart);
			}
		}

		message.setContent(multipart);

		Transport transport = session.getTransport("smtp");
		transport.connect(host, userName, password);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}

	public static final void sendMail(List<String> recipients, String subject,
			String template, HashMap<String, String> map,
			List<String> attachments) throws Exception {
		String htmlContent = Util.getTemplateContent(template);

		for (Entry<String, String> entry : map.entrySet()) {
			htmlContent = htmlContent.replace(entry.getKey(), entry.getValue());
		}

		sendMail(recipients, subject, htmlContent, attachments);
	}

	public static final void sendErrorLogMail(String subject, String html,
			List<String> attachments) throws Exception {
		final String mailto = MailConfig.getProperty(MailConfig.MAIL_TO);
		final List<String> recipients = new LinkedList<String>();
		recipients.add(mailto);
		sendMail(recipients, subject, html, attachments);
	}

	public static final void sendErrorLogMail(String subject, String template,
			HashMap<String, String> map, List<String> attachments)
			throws Exception {
		String htmlContent = Util.getTemplateContent(template);
		for (Entry<String, String> entry : map.entrySet()) {
			htmlContent = htmlContent.replace(entry.getKey(), entry.getValue());
		}
		sendErrorLogMail(subject, htmlContent, attachments);
	}
}
