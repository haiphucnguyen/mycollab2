package com.esofthead.mycollab.mock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esofthead.mycollab.module.mail.EmailAttachementSource;
import com.esofthead.mycollab.module.mail.service.SystemMailService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

@Service
public class MockSystemMailService implements SystemMailService {

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			String[] toEmail, String[] toName, String subject, String html,
			List<EmailAttachementSource> attachment) {
	}

	@Override
	public void sendHTMLMail(String fromEmail, String fromName,
			List<SimpleUser> users, String subject, String html,
			List<EmailAttachementSource> attachment) {
	}

}
