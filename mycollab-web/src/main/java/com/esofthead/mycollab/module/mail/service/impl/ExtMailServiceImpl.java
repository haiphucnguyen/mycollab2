/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.mail.service.impl;

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
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
