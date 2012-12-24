package com.esofthead.mycollab.common.service;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.MailRecipientField;

@Service
public interface MailService {
	void sendMail(List<MailRecipientField> toFields,
			List<MailRecipientField> ccFields,
			List<MailRecipientField> bccFields, String subject, String content)
			throws EmailException;

}
