/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.mail.service.impl;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.module.mail.Mailer;
import com.esofthead.mycollab.module.mail.service.ExtMailService;

/**
 * 
 * @author haiphucnguyen
 */
public class ExtMailServiceImpl extends AbstractMailService implements
		ExtMailService {

	@Override
	protected Mailer getMailer() {
		String smtphost, port, username, password;
		smtphost = ApplicationProperties.getProperty(ApplicationProperties.RELAYMAIL_SMTPHOST);
		port = ApplicationProperties.getProperty(ApplicationProperties.RELAYMAIL_PORT);
		username = ApplicationProperties.getProperty(ApplicationProperties.RELAYMAIL_USERNAME);
		password = ApplicationProperties.getProperty(ApplicationProperties.RELAYMAIL_PASSWORD);
		port = ApplicationProperties.getProperty(ApplicationProperties.RELAYMAIL_PORT);
		
		if (null == smtphost || smtphost.trim().length() == 0
				|| null == port || port.trim().length() == 0
				|| null == username || username.trim().length() == 0
				|| null == password || password.trim().length() == 0
				|| null == port || port.trim().length() == 0)
		{
			smtphost = ApplicationProperties.getProperty(ApplicationProperties.MAIL_SMTPHOST);
			port = ApplicationProperties.getProperty(ApplicationProperties.MAIL_PORT);
			username = ApplicationProperties.getProperty(ApplicationProperties.MAIL_USERNAME);
			password = ApplicationProperties.getProperty(ApplicationProperties.MAIL_PASSWORD);
			port = ApplicationProperties.getProperty(ApplicationProperties.MAIL_PORT);
		}
		
		try {
			new Mailer(smtphost, username, password, Integer.parseInt(port));
		} catch (Exception ex) {
			
		}
		
		return new Mailer(smtphost, username, password, 587);
	}
	

}


