package com.esofthead.mycollab.module.mail.service.impl;

import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.module.mail.Mailer;
import com.esofthead.mycollab.module.mail.service.SystemMailService;

@Service
public class SystemMailServiceImpl extends AbstractMailService implements
		SystemMailService {
	private static Mailer gmailer;

	static {
		gmailer = new Mailer(
				ApplicationProperties.getProperty(ApplicationProperties.MAIL_SMTPHOST),
				ApplicationProperties.getProperty(ApplicationProperties.MAIL_USERNAME),
				ApplicationProperties.getProperty(ApplicationProperties.MAIL_PASSWORD), 
				Integer.parseInt(ApplicationProperties.getProperty(ApplicationProperties.MAIL_PORT)),
				true);
	}

	@Override
	protected Mailer getMailer() {
		return gmailer;
	}
}
