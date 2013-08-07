package com.esofthead.mycollab.module.mail.service;

import java.util.List;

import com.esofthead.mycollab.common.domain.RelayEmailWithBLOBs;

public interface MailRelayService {
	void saveRelayEmail(int sAccountId, String[] toNames, String[] toEmails,
			String subject, String bodyContent);

	List<RelayEmailWithBLOBs> getRelayEmails();
	
	void cleanEmails();
}
