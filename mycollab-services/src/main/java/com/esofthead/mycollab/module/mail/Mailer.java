package com.esofthead.mycollab.module.mail;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailConstants;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.configuration.EmailConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

public class Mailer {
	private static Logger log = LoggerFactory.getLogger(Mailer.class);
	protected String host;
	protected String username = null;
	protected String password = null;
	protected boolean isTLS = false;
	protected int port;

	public Mailer(EmailConfiguration emailConf) {
		this.host = emailConf.getHost();
		this.username = emailConf.getUser();
		this.password = emailConf.getPassword();
		this.isTLS = emailConf.isTls();
		this.port = emailConf.getPort();
	}

	private HtmlEmail getBasicEmail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName(host);
			email.setFrom(fromEmail, fromName);
			email.setCharset(EmailConstants.UTF_8);
			for (int i = 0; i < toEmail.size(); i++) {
				if (isValidate(toEmail.get(i).getEmail())
						&& isValidate(toEmail.get(i).getName())) {
					email.addTo(toEmail.get(i).getEmail(), toEmail.get(i)
							.getName());
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
						email.addBcc(bccEmail.get(i).getEmail(), bccEmail
								.get(i).getName());
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
			email.setBounceAddress("hainguyen@esofthead.com");

			if (html != null && !html.equals("")) {
				email.setHtmlMsg(html);
			}

			return email;
		} catch (EmailException e) {
			throw new MyCollabException(e);
		}
	}

	public void sendHTMLMail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html) {
		try {
			HtmlEmail email = getBasicEmail(fromEmail, fromName, toEmail,
					ccEmail, bccEmail, subject, html);
			email.setBounceAddress("hainguyen@esofthead.com");
			email.send();
		} catch (EmailException e) {
			throw new MyCollabException(e);
		}
	}

	public void sendHTMLMail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html,
			List<EmailAttachementSource> attachments) {
		try {
			if (attachments == null || attachments.isEmpty()) {
				sendHTMLMail(fromEmail, fromName, toEmail, ccEmail, bccEmail,
						subject, html);
			} else {
				HtmlEmail email = getBasicEmail(fromEmail, fromName, toEmail,
						ccEmail, bccEmail, subject, html);

				for (EmailAttachementSource attachment : attachments) {
					email.attach(attachment.getAttachmentObj());
				}
				email.setBounceAddress("hainguyen@esofthead.com");
				email.send();
			}
		} catch (EmailException e) {
			throw new MyCollabException(e);
		}
	}

	public void sendHTMLMail(String fromEmail, String fromName,
			List<SimpleUser> users, String subject, String html,
			List<EmailAttachementSource> attachment) {

		List<MailRecipientField> lstRecipient = new ArrayList<MailRecipientField>();
		for (int i = 0; i < users.size(); i++) {
			String mail = users.get(i).getEmail();
			String mailName = (users.get(i).getDisplayName() == null || users
					.get(i).getDisplayName().equals("")) ? mail : users.get(i)
					.getDisplayName();
			lstRecipient.add(new MailRecipientField(mail, mailName));
		}

		this.sendHTMLMail(fromEmail, fromName, lstRecipient, null, null,
				subject, html, attachment);
	}

	private boolean isValidate(String val) {
		return (val != null) && (val.trim().length() > 0);
	}
}
