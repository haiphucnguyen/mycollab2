package com.esofthead.monitoring;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.configuration.EmailConfiguration;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.mail.EmailAttachementSource;
import com.esofthead.mycollab.module.mail.FileEmailAttachmentSource;
import com.esofthead.mycollab.module.mail.Mailer;

public class MyCollabMonitoringReporter {
	private static Logger log = LoggerFactory
			.getLogger(MyCollabMonitoringReporter.class);

	public void sendDailyReport(String attachment) {
		/*
		 * do send mail here
		 */
		EmailConfiguration emailConfiguration = SiteConfiguration
				.getEmailConfiguration();

		Mailer mailer = new Mailer(SiteConfiguration.getEmailConfiguration());

		File file = new File(attachment);
		List<EmailAttachementSource> emailAttachmentSource = null;
		if (file != null) {
			emailAttachmentSource = new ArrayList<EmailAttachementSource>();
			emailAttachmentSource.add(new FileEmailAttachmentSource(file));
		}

		try {
			mailer.sendHTMLMail(emailConfiguration.getUser(),
					"eSofthead reporter",
					Arrays.asList(new MailRecipientField(SiteConfiguration
							.getSendErrorEmail(), "eSofthead")), null, null,
					"Daily Report Monitoring - " + toDayString(),
					"<h1>This is the sample of daily monitoring report</h1>",
					emailAttachmentSource);
		} catch (Exception e) {
			log.error("Send monitoring failed", e);
		} finally {

			if (file.exists()) {
				file.deleteOnExit();
			}
		}
	}

	private static String toDayString() {
		final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}
}
