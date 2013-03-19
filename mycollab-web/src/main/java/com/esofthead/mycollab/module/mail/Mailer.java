package com.esofthead.mycollab.module.mail;

import java.util.List;

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
		
		if (html != null && !html.equals("")) {
			email.setHtmlMsg(html);
		}
		email.send();
	}

	public void sendHTMLMail(String fromEmail, String fromName,
			String[] toEmail, String[] toName, String subject, String html,
			List<EmailAttachementSource> attachments) throws EmailException {
		if (attachments == null || attachments.isEmpty()) {
			sendHTMLMail(fromEmail, fromName, toEmail, toName, subject, html);
		} else {
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
			if (html != null && !html.equals("")) {
				email.setHtmlMsg(html);
			}
			
			for (EmailAttachementSource attachment : attachments) {
				email.attach(attachment.getAttachmentObj());
			}
			
			email.send();
		}
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
