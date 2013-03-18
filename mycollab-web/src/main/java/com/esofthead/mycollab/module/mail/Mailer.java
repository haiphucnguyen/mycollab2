package com.esofthead.mycollab.module.mail;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mailer {
	private static Logger log = LoggerFactory.getLogger(Mailer.class);
	protected String host;
	protected String username = null;
	protected String password = null;
	protected boolean isTLS = false;
	protected int port;

	public Mailer(String host, String username, String password, int port,
			boolean isTLS) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.isTLS = isTLS;
		this.port = port;
	}

	public Mailer(String host, String username, String password, int port) {
		this(host, username, password, port, false);
	}

	public Mailer(String host) {
		this(host, null, null, 587, false);
	}

	public void sendHTMLMail(String fromEmail, String fromName,
			String[] toEmail, String[] toName, String subject, String html)
			throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(host);
		email.setFrom(fromEmail, fromName);
		for (int i = 0; i < toEmail.length; i++) {
			if (isValidate(toEmail[i]) && isValidate(toName[i])) {
				email.addTo(toEmail[i], toName[i]);
			} else {
				log.error("Invalid email input: " + toEmail[i] + "---"
						+ toName[i]);
			}
		}
		if (username != null) {
			email.setAuthentication(username, password);
		}
		email.setStartTLSEnabled(isTLS);
		email.setSubject(subject);
		email.setHtmlMsg(html);
		email.send();
	}

	public void sendHTMLMail(String fromEmail, String fromName,
			String[] toEmail, String[] toName, String subject, String html,
			List<String> attachments) throws Exception {

		final String charSet = "UTF-8";

		final Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.gmail", host);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", String.valueOf(port));
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.password", password);
		
		if (465 == port) {
			props.put("mail.smtp.socketFactory.port", port);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.starttls.enable", "true");
		}

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(fromEmail, fromName, charSet));
		message.setSubject(subject);

		InternetAddress[] toRecipients = new InternetAddress[toEmail.length];
		for (int i = 0; i < toEmail.length; i++) {
			toRecipients[i] = new InternetAddress(toEmail[i], toName[i], charSet);
		}
		message.setRecipients(Message.RecipientType.TO, toRecipients);

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
		transport.connect(host, username, password);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}

	private boolean isValidate(String val) {
		return (val != null) && (val.trim().length() > 0);
	}

	public static void main(String[] args) throws EmailException {
		Mailer mailer = new Mailer("smtp.gmail.com", "mail@esofthead.com",
				"esofthead321", 465, true);
		mailer.sendHTMLMail("mail@esofthead.com", "A",
				new String[] { "hainguyen@esofthead.com" },
				new String[] { "Hai Nguyen" }, "AAA", "bbb");
	}
}
