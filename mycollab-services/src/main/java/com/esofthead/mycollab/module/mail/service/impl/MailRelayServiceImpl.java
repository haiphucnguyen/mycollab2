package com.esofthead.mycollab.module.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.RelayEmailMapper;
import com.esofthead.mycollab.common.domain.RelayEmailExample;
import com.esofthead.mycollab.common.domain.RelayEmailWithBLOBs;
import com.esofthead.mycollab.module.mail.service.MailRelayService;
import com.thoughtworks.xstream.XStream;

@Service
public class MailRelayServiceImpl implements MailRelayService {

	@Autowired
	private RelayEmailMapper relayEmailMapper;

	@Override
	public void saveRelayEmail(String[] toNames, String[] toEmails,
			String subject, String bodyContent) {
		RelayEmailWithBLOBs relayEmail = new RelayEmailWithBLOBs();
		relayEmail.setFromemail("noreply@esofthead.com");
		relayEmail.setFromname("noreply@esofthead.com");

		XStream xmlSerializer = new XStream();
		String recipientList = xmlSerializer.toXML(new String[][] { toEmails,
				toNames });
		relayEmail.setRecipients(recipientList);
		relayEmail.setSubject(subject);
		relayEmail.setBodycontent(bodyContent);
		relayEmail.setSaccountid(1);

		relayEmailMapper.insert(relayEmail);
	}

	@Override
	public List<RelayEmailWithBLOBs> getRelayEmails() {
		RelayEmailExample ex = new RelayEmailExample();
		return relayEmailMapper.selectByExampleWithBLOBs(ex);
	}

	@Override
	public void cleanEmails() {
		relayEmailMapper.deleteByExample(new RelayEmailExample());
	}

}
