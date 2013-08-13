package com.esofthead.mycollab.module.mail.service;

import java.util.List;

import com.esofthead.mycollab.common.domain.RelayEmailWithBLOBs;
import com.esofthead.mycollab.core.persistence.service.IService;

public interface MailRelayService extends IService {
	void saveRelayEmail(int sAccountId, String[] toNames, String[] toEmails,
			String subject, String bodyContent);

	List<RelayEmailWithBLOBs> getRelayEmails();

	void cleanEmails();
}
