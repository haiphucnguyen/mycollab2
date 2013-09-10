package com.esofthead.mycollab.schedule.email.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.RelayEmailWithBLOBs;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.mail.Mailer;
import com.esofthead.mycollab.module.mail.service.RelayEmailService;
import com.esofthead.mycollab.schedule.email.ScheduleConfig;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailsAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.thoughtworks.xstream.XStream;

@Service
public class ScheduleSendingRelayEmailsServiceImpl {
	private static final Logger log = LoggerFactory
			.getLogger(ScheduleSendingRelayEmailsServiceImpl.class);

	@Autowired
	private RelayEmailService mailRelayService;

	@Scheduled(fixedDelay = ScheduleConfig.RUN_EMAIL_RELAY_INTERVAL)
	public void sendRelayEmails() {
		List<RelayEmailWithBLOBs> relayEmails = mailRelayService
				.getRelayEmails();
		mailRelayService.cleanEmails();
		for (RelayEmailWithBLOBs relayEmail : relayEmails) {
			if (relayEmail.getEmailhandlerbean() == null) {
				String recipientVal = relayEmail.getRecipients();
				XStream xstream = new XStream();
				String[][] recipientArr = (String[][]) xstream
						.fromXML(recipientVal);
				try {
					List<MailRecipientField> toMailList = new ArrayList<MailRecipientField>();
					for (int i = 0; i < recipientArr[0].length; i++) {
						toMailList.add(new MailRecipientField(
								recipientArr[0][i], recipientArr[1][i]));
					}

					Mailer mailer = new Mailer(
							SiteConfiguration.getEmailConfiguration());
					mailer.sendHTMLMail(relayEmail.getFromemail(),
							relayEmail.getFromname(), toMailList, null, null,
							relayEmail.getSubject(),
							relayEmail.getBodycontent(), null);
				} catch (Exception e) {
					log.error("Error when send relay email", e);
				}
			} else {
				try {
					SendingRelayEmailsAction emailNotificationAction = (SendingRelayEmailsAction) getSpringBean(Class
							.forName(relayEmail.getEmailhandlerbean()));
					emailNotificationAction.sendEmail(relayEmail);
				} catch (ClassNotFoundException e) {
					throw new MyCollabException(e);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static Object getSpringBean(Class clazz) {
		ApplicationContext context = ApplicationContextUtil
				.getApplicationContext();
		return context.getBean(clazz);
	}
}
