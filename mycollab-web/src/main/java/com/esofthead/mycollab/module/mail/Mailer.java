package com.esofthead.mycollab.module.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class Mailer {
	protected String host;
	protected String username = null;
	protected String password = null;
	protected boolean isTLS = false;

	public Mailer(String host, String username, String password, boolean isTLS) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.isTLS = isTLS;
	}

	public Mailer(String host, String username, String password) {
		this(host, username, password, false);
	}

	public Mailer(String host) {
		this(host, null, null, false);
	}

	public void sendHTMLMail(String fromEmail, String fromName,
			String[] toEmail, String[] toName, String subject, String html)
			throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(host);
		email.setFrom(fromEmail, fromName);
		for (int i = 0; i < toEmail.length; i++) {
			email.addTo(toEmail[i], toName[i]);
		}
		if (username != null) {
			email.setAuthentication(username, password);
		}
		email.setStartTLSEnabled(isTLS);
		email.setSubject(subject);
		email.setHtmlMsg(html);
		email.send();
	}
}
