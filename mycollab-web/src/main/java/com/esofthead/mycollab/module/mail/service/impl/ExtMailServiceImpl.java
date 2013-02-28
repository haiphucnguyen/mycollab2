/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.mail.service.impl;

import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.mail.Mailer;
import com.esofthead.mycollab.module.mail.service.ExtMailService;

/**
 * 
 * @author haiphucnguyen
 */
@Service
public class ExtMailServiceImpl extends AbstractMailService implements
		ExtMailService {

	@Override
	protected Mailer getMailer() {
		String smtphost = ApplicationProperties
				.getProperty(ApplicationProperties.RELAYMAIL_SMTPHOST);
		String port = ApplicationProperties
				.getProperty(ApplicationProperties.RELAYMAIL_PORT);
		String username = ApplicationProperties
				.getProperty(ApplicationProperties.RELAYMAIL_USERNAME);
		String password = ApplicationProperties
				.getProperty(ApplicationProperties.RELAYMAIL_PASSWORD);
		boolean isTLS = Boolean.parseBoolean(ApplicationProperties.getProperty(
				ApplicationProperties.RELAYMAIL_IS_TLS, "false"));

		if (null == smtphost || smtphost.trim().length() == 0 || null == port
				|| port.trim().length() == 0 || null == username
				|| username.trim().length() == 0 || null == password
				|| password.trim().length() == 0 || null == port
				|| port.trim().length() == 0) {
			smtphost = ApplicationProperties
					.getProperty(ApplicationProperties.MAIL_SMTPHOST);
			port = ApplicationProperties
					.getProperty(ApplicationProperties.MAIL_PORT);
			username = ApplicationProperties
					.getProperty(ApplicationProperties.MAIL_USERNAME);
			password = ApplicationProperties
					.getProperty(ApplicationProperties.MAIL_PASSWORD);
			port = ApplicationProperties
					.getProperty(ApplicationProperties.MAIL_PORT);
			isTLS = Boolean.parseBoolean(ApplicationProperties.getProperty(
					ApplicationProperties.MAIL_IS_TLS, "false"));
		}
		try {
			return new Mailer(smtphost, username, password,
					Integer.parseInt(port), isTLS);
		} catch (Exception ex) {
			throw new MyCollabException(ex);
		}
	}

}
