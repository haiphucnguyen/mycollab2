package com.esofthead.mycollab.module.mail;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.MailRecipientField;

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

	private HtmlEmail getBasicEmail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html)
			throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(host);
		email.setFrom(fromEmail, fromName);
		for (int i = 0; i < toEmail.size(); i++) {
			if (isValidate(toEmail.get(i).getEmail())
					&& isValidate(toEmail.get(i).getName())) {
				email.addTo(toEmail.get(i).getEmail(), toEmail.get(i).getName());
			} else {
				log.error("Invalid to email input: "
						+ toEmail.get(i).getEmail() + "---"
						+ toEmail.get(i).getName());
			}
		}

		if (ccEmail != null && ccEmail.size() > 0) {
			for (int i = 0; i < ccEmail.size(); i++) {
				if (isValidate(ccEmail.get(i).getEmail())
						&& isValidate(ccEmail.get(i).getName())) {
					email.addCc(ccEmail.get(i).getEmail(), ccEmail.get(i)
							.getName());
				} else {
					log.error("Invalid cc email input: "
							+ ccEmail.get(i).getEmail() + "---"
							+ ccEmail.get(i).getName());
				}
			}
		}

		if (bccEmail != null && bccEmail.size() > 0) {
			for (int i = 0; i < bccEmail.size(); i++) {
				if (isValidate(bccEmail.get(i).getEmail())
						&& isValidate(bccEmail.get(i).getName())) {
					email.addBcc(bccEmail.get(i).getEmail(), bccEmail.get(i)
							.getName());
				} else {
					log.error("Invalid bcc email input: "
							+ bccEmail.get(i).getEmail() + "---"
							+ bccEmail.get(i).getName());
				}
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

		return email;
	}

	public void sendHTMLMail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html)
			throws EmailException {

		HtmlEmail email = getBasicEmail(fromEmail, fromName, toEmail, ccEmail,
				bccEmail, subject, html);
		email.send();
	}

	public void sendHTMLMail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html,
			List<EmailAttachementSource> attachments) throws EmailException {
		if (attachments == null || attachments.isEmpty()) {
			sendHTMLMail(fromEmail, fromName, toEmail, ccEmail, bccEmail,
					subject, html);
		} else {
			HtmlEmail email = getBasicEmail(fromEmail, fromName, toEmail,
					ccEmail, bccEmail, subject, html);

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
		mailer.sendHTMLMail("mail@esofthead.com", "A", Arrays
				.asList(new MailRecipientField("linhnguyen@esofthead.com",
						"Linh Nguyen")), Arrays.asList(new MailRecipientField(
				"manhlinh1905Manu@gmail.com", "Linh Nguyen")), null, "Test",
				"bbb");
	}
}
