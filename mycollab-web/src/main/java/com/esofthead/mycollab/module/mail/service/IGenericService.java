package com.esofthead.mycollab.module.mail.service;

import java.util.List;

import com.esofthead.mycollab.module.user.domain.SimpleUser;

public interface IGenericService {
	void sendHTMLMail(String fromEmail, String fromName, String[] toEmail,
			String[] toName, String subject, String html);

	void sendHTMLMail(String fromEmail, String fromName,
			List<SimpleUser> users, String subject, String html);
}
