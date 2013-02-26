package com.esofthead.mycollab.module.mail.service.impl;

import java.util.List;

import org.apache.commons.mail.EmailException;

import com.esofthead.mycollab.module.mail.Mailer;
import com.esofthead.mycollab.module.mail.service.IGenericService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

public abstract class AbstractMailService implements IGenericService {

	protected abstract Mailer getMailer();

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			String[] toEmail, String[] toName, String subject, String html) {
		try {
			getMailer().sendHTMLMail(fromEmail, fromName, toEmail, toName,
					subject, html);
		} catch (EmailException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			List<SimpleUser> users, String subject, String html) {
		String[] toEmails = new String[users.size()];
		String[] toNames = new String[users.size()];

		for (int i = 0; i < users.size(); i++) {
			toEmails[i] = users.get(i).getEmail();
			toNames[i] = users.get(i).getDisplayName();
		}

		this.sendHTMLMail(fromEmail, fromName, toEmails, toNames, subject, html);
	}
}
