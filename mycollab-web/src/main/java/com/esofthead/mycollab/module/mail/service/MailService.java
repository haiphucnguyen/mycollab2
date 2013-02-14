package com.esofthead.mycollab.module.mail.service;

public interface MailService {
	/**
	 * 
	 * @param fromEmail
	 * @param fromName
	 * @param toEmail
	 * @param toName
	 * @param subject
	 * @param html
	 */
	void sendHTMLMail(String fromEmail, String fromName, String[] toEmail,
			String[] toName, String subject, String html);
}
