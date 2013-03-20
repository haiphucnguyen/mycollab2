package com.esofthead.mycollab.mock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.module.mail.EmailAttachementSource;
import com.esofthead.mycollab.module.mail.service.SystemMailService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

@Service
public class MockSystemMailService implements SystemMailService {

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
			List<MailRecipientField> bccEmail, String subject, String html,
			List<EmailAttachementSource> attachments) {
	}

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			List<SimpleUser> users, String subject, String html,
			List<EmailAttachementSource> attachment) {
	}

}
