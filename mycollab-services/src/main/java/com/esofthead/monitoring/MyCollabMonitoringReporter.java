/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
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
