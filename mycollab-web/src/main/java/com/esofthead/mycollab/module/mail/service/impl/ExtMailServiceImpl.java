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
				.getString(ApplicationProperties.RELAYMAIL_SMTPHOST);
		String port = ApplicationProperties
				.getString(ApplicationProperties.RELAYMAIL_PORT);
		String username = ApplicationProperties
				.getString(ApplicationProperties.RELAYMAIL_USERNAME);
		String password = ApplicationProperties
				.getString(ApplicationProperties.RELAYMAIL_PASSWORD);
		boolean isTLS = Boolean.parseBoolean(ApplicationProperties.getString(
				ApplicationProperties.RELAYMAIL_IS_TLS, "false"));

		if (null == smtphost || smtphost.trim().length() == 0 || null == port
				|| port.trim().length() == 0 || null == username
				|| username.trim().length() == 0 || null == password
				|| password.trim().length() == 0 || null == port
				|| port.trim().length() == 0) {
			smtphost = ApplicationProperties
					.getString(ApplicationProperties.MAIL_SMTPHOST);
			port = ApplicationProperties
					.getString(ApplicationProperties.MAIL_PORT);
			username = ApplicationProperties
					.getString(ApplicationProperties.MAIL_USERNAME);
			password = ApplicationProperties
					.getString(ApplicationProperties.MAIL_PASSWORD);
			port = ApplicationProperties
					.getString(ApplicationProperties.MAIL_PORT);
			isTLS = Boolean.parseBoolean(ApplicationProperties.getString(
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
