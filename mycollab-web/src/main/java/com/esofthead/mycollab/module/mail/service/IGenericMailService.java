package com.esofthead.mycollab.module.mail.service;

import java.util.List;

import com.esofthead.mycollab.module.mail.EmailAttachementSource;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

public interface IGenericMailService {
	void sendHTMLMail(String fromEmail, String fromName, String[] toEmail,
			String[] toName, String subject, String html,
			List<EmailAttachementSource> attachment);

	void sendHTMLMail(String fromEmail, String fromName,
			List<SimpleUser> users, String subject, String html,
			List<EmailAttachementSource> attachment);
}
