package com.esofthead.monitoring;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.module.mail.Mailer;

public class MyCollabMonitoringReporter {
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
				Integer.parseInt(port));
		try {
			mailer.sendHTMLMail(userName, "eSofthead reporter",
					new String[] { userName },
					new String[] { "eSofthead" }, "Daily Report Monitoring - "
							+ toDayString(), "<h1>This is the sample of daily monitoring report</h1>",
					Collections.singletonList(attachment));
		} catch (Exception e) {
			System.out.println("\r\n\r\n\r\n");
			System.out.println(e.getMessage());
			System.out.println("\r\n\r\n\r\n");
		} finally {
			File file = new File(attachment);
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
