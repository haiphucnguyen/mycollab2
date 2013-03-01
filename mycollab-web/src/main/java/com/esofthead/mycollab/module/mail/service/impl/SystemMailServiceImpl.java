package com.esofthead.mycollab.module.mail.service.impl;

import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.module.mail.Mailer;
import com.esofthead.mycollab.module.mail.service.SystemMailService;

@Service
public class SystemMailServiceImpl extends AbstractMailService implements
		SystemMailService {
	private static Mailer gmailer;

	@Override
	protected Mailer getMailer() {
		if (gmailer == null) {
			gmailer = new Mailer(
					ApplicationProperties
							.getProperty(ApplicationProperties.MAIL_SMTPHOST),
					ApplicationProperties
							.getProperty(ApplicationProperties.MAIL_USERNAME),
					ApplicationProperties
							.getProperty(ApplicationProperties.MAIL_PASSWORD),
					Integer.parseInt(ApplicationProperties
							.getProperty(ApplicationProperties.MAIL_PORT)),
					Boolean.parseBoolean(ApplicationProperties.getProperty(
							ApplicationProperties.MAIL_IS_TLS, "false")));
		}
		return gmailer;
	}
}
