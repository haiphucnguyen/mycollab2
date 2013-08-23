/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.mail.service.impl;

import org.springframework.stereotype.Service;

import com.esofthead.mycollab.configuration.EmailConfiguration;
import com.esofthead.mycollab.configuration.SiteConfiguration;
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
		EmailConfiguration emailConfiguration = SiteConfiguration
				.getRelayEmailConfiguration();

		return new Mailer(emailConfiguration);

	}

}
