package com.esofthead.mycollab.schedule.jobs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public class BillingSendEmailFactory {

	private static final String AFTER_25_30DAY = "templates/email/billing/notificationAfter25_30DayTrial.mt";
	private static final String AFTER_32DAY = "templates/email/billing/notificationAfter32DayTrial.mt";
	private Integer afterDay;
	private BillingAccountWithOwners account;
	private ExtMailService extMailService;

	private static Logger log = LoggerFactory
			.getLogger(BillingSendEmailFactory.class);

	public BillingSendEmailFactory(BillingAccountWithOwners account,
			Integer afterDay) {
		this.afterDay = afterDay;
		this.account = account;
		this.extMailService = ApplicationContextUtil
				.getSpringBean(ExtMailService.class);
	}

	public void sendingEmail() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		if (afterDay == 30 || afterDay == 25) {
			for (SimpleUser user : account.getOwners()) {
				log.error("Send mail after " + afterDay
						+ "day for username {} , mail {}", user.getUsername(),
						user.getEmail());
				TemplateGenerator templateGenerator = new TemplateGenerator(
						"Your trial is about to end", AFTER_25_30DAY);
				templateGenerator.putVariable("account", account);

				String link = "https://" + account.getSubdomain()
						+ ".mycollab.com";
				Calendar cal = Calendar
						.getInstance(TimeZone.getTimeZone("UTC"));
				if (afterDay == 25) {
					cal.add(Calendar.DATE, 5);
				}
				templateGenerator.putVariable("expireDay",
						df.format(cal.getTime()));
				templateGenerator.putVariable("userName", user.getUsername());
				templateGenerator.putVariable("link", link);
				extMailService.sendHTMLMail("noreply@esofthead.com",
						"noreply@esofthead.com", Arrays
								.asList(new MailRecipientField(user.getEmail(),
										user.getDisplayName())), null, null,
						templateGenerator.generateSubjectContent(),
						templateGenerator.generateBodyContent(), null);
			}
		} else if (afterDay == 32) {
			for (SimpleUser user : account.getOwners()) {
				log.error("Send mail after 32day for username {} , mail {}",
						user.getUsername(), user.getEmail());
				TemplateGenerator templateGenerator = new TemplateGenerator(
						"Your trial has ended", AFTER_32DAY);
				templateGenerator.putVariable("account", account);
				templateGenerator.putVariable("userName", user.getUsername());
				String link = "https://" + account.getSubdomain()
						+ ".mycollab.com";
				templateGenerator.putVariable("link", link);

				extMailService.sendHTMLMail("noreply@esofthead.com",
						"noreply@esofthead.com", Arrays
								.asList(new MailRecipientField(user.getEmail(),
										user.getDisplayName())), null, null,
						templateGenerator.generateSubjectContent(),
						templateGenerator.generateBodyContent(), null);
			}
		}
	}
}
