package com.esofthead.monitoring;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.ApplicationProperties;
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
		String host = ApplicationProperties
				.getProperty(ApplicationProperties.MAIL_SMTPHOST);
		String userName = ApplicationProperties
				.getProperty(ApplicationProperties.MAIL_USERNAME);
		String password = ApplicationProperties
				.getProperty(ApplicationProperties.MAIL_PASSWORD);
		String port = ApplicationProperties
				.getProperty(ApplicationProperties.MAIL_PORT);

		Mailer mailer = new Mailer(host, userName, password,
				Integer.parseInt(port), true);

		File file = new File(attachment);
		List<EmailAttachementSource> emailAttachmentSource = null;
		if (file != null) {
			emailAttachmentSource = new ArrayList<EmailAttachementSource>();
			emailAttachmentSource.add(new FileEmailAttachmentSource(file));
		}
		
		try {
			mailer.sendHTMLMail(userName, "eSofthead reporter",
					new String[] { ApplicationProperties
							.getProperty(ApplicationProperties.ERROR_SENDTO) },
					new String[] { "eSofthead" }, "Daily Report Monitoring - "
							+ toDayString(),
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

	static String toDayString() {
		final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}
}
