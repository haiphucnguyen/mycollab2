package com.esofthead.mycollab.schedule.jobs;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.RelayEmailWithBLOBs;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.mail.Mailer;
import com.esofthead.mycollab.module.mail.service.MailRelayService;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailsAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.thoughtworks.xstream.XStream;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class SendingRelayEmailJob extends QuartzJobBean {
	private static Logger log = LoggerFactory
			.getLogger(SendingRelayEmailJob.class);

	@Autowired
	private MailRelayService mailRelayService;

	@Override
	protected void executeInternal(JobExecutionContext context) {
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
					SendingRelayEmailsAction emailNotificationAction = (SendingRelayEmailsAction) ApplicationContextUtil
							.getSpringBean(Class.forName(relayEmail
									.getEmailhandlerbean()));
					emailNotificationAction.sendEmail(relayEmail);
				} catch (ClassNotFoundException e) {
					throw new MyCollabException(e);
				}
			}
		}
	}
}
