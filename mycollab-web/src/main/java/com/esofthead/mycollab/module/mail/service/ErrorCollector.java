package com.esofthead.mycollab.module.mail.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ErrorCollector {
	private static final String MESSAGE_SUBJECT = "Error on mail campaign";
	
	public static final void sendErrorMail(String responseJson, String errorMessage) {
		
		final String mailContent = "Datetime: %s\r\n\r\nJason Response:\r\n%s\r\n\r\nError message:\r\n%s";
		final CampaignConfig config = CampaignConfig.loadConfig();
		if (null == config)
			return;
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(config.getUserName(),
								config.getPassword());
					}
				});
		
		String messageContent = String.format(mailContent, now(), responseJson, errorMessage);
		
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(config.getMailForm()));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(config.getMailTo()));
			message.setSubject(MESSAGE_SUBJECT);
			message.setText(messageContent);

			Transport.send(message);
		} catch (Exception e) {
		}
	}
	
	public static final String now() {
		final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());

	}
}
