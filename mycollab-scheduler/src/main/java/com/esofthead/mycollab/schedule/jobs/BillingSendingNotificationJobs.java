package com.esofthead.mycollab.schedule.jobs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class BillingSendingNotificationJobs extends QuartzJobBean {

	private static final Integer DATE_REMIND_FOR_FREEPLAN_1ST = 25;
	private static final Integer DATE_REMIND_FOR_FREEPLAN_2ND = 30;
	private static final Integer DATE_NOTIFY_EXPIRE = 32;
	private static final Integer NUM_DAY_FREE_TRIAL = 30;

	private static Logger log = LoggerFactory
			.getLogger(BillingSendingNotificationJobs.class);

	@Autowired
	private BillingService billingService;

	@Autowired
	private BillingAccountService billingAccountService;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {

		List<BillingAccountWithOwners> trialAccountsWithOwners = billingService
				.getTrialAccountsWithOwners();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

		if (trialAccountsWithOwners != null
				&& trialAccountsWithOwners.size() > 0) {
			for (BillingAccountWithOwners account : trialAccountsWithOwners) {
				log.debug("Check whether account exceed 25 days to remind user upgrade account");
				cal.add(Calendar.DATE, (-1) * DATE_REMIND_FOR_FREEPLAN_1ST);
				Date dateRemid1st = cal.getTime();
				if (df.format(account.getCreatedtime()).equals(
						df.format(dateRemid1st))) {
					AccountBillingSendEmail notificationFactory = new AccountBillingSendEmail(
							account, DATE_REMIND_FOR_FREEPLAN_1ST);
					notificationFactory.sendingEmail();
					cal.add(Calendar.DATE, DATE_REMIND_FOR_FREEPLAN_1ST);
					continue;
				}
				log.debug("Check whether account exceed 30 days to inform him it is the end of day to upgrade account");
				cal.add(Calendar.DATE, DATE_REMIND_FOR_FREEPLAN_1ST
						- DATE_REMIND_FOR_FREEPLAN_2ND);
				Date dateRemind2nd = cal.getTime();
				if (df.format(account.getCreatedtime()).equals(
						df.format(dateRemind2nd))) {
					AccountBillingSendEmail notificationFactory = new AccountBillingSendEmail(
							account, DATE_REMIND_FOR_FREEPLAN_2ND);
					notificationFactory.sendingEmail();
					cal.add(Calendar.DATE, DATE_REMIND_FOR_FREEPLAN_2ND);
					continue;
				}
				log.debug("Check whether account exceed 32 days to convert to basic plan");
				cal.add(Calendar.DATE, DATE_REMIND_FOR_FREEPLAN_2ND
						- DATE_NOTIFY_EXPIRE);
				Date dateExpire = cal.getTime();
				if (df.format(account.getCreatedtime()).equals(
						df.format(dateExpire))) {
					BillingAccount billingAccount = billingAccountService
							.findByPrimaryKey(account.getId(), account.getId());
					BillingPlan freeBillingPlan = billingService
							.getFreeBillingPlan();
					billingAccount.setBillingplanid(freeBillingPlan.getId());
					billingAccountService.updateWithSession(billingAccount, "");
					AccountBillingSendEmail notificationFactory = new AccountBillingSendEmail(
							account, DATE_NOTIFY_EXPIRE);
					notificationFactory.sendingEmail();
					cal.add(Calendar.DATE, DATE_NOTIFY_EXPIRE);
					continue;
				}
				cal.add(Calendar.DATE, DATE_NOTIFY_EXPIRE);
			}
		}
	}

	public class AccountBillingSendEmail {
		private static final String remindAccountIsAboutEndTemplate = "templates/email/billing/remindAccountIsAboutExpiredNotification.mt";
		private static final String informAccountIsExpiredTemplate = "templates/email/billing/informAccountIsExpiredNotification.mt";
		private Integer afterDay;
		private BillingAccountWithOwners account;
		private ExtMailService extMailService;

		public AccountBillingSendEmail(BillingAccountWithOwners account,
				Integer afterDay) {
			this.afterDay = afterDay;
			this.account = account;
			this.extMailService = ApplicationContextUtil
					.getSpringBean(ExtMailService.class);
		}

		public void sendingEmail() {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			if (afterDay == BillingSendingNotificationJobs.DATE_REMIND_FOR_FREEPLAN_2ND
					|| afterDay == BillingSendingNotificationJobs.DATE_REMIND_FOR_FREEPLAN_1ST) {
				for (SimpleUser user : account.getOwners()) {
					log.error("Send mail after " + afterDay
							+ "day for username {} , mail {}",
							user.getUsername(), user.getEmail());
					TemplateGenerator templateGenerator = new TemplateGenerator(
							"Your trial is about to end",
							remindAccountIsAboutEndTemplate);
					templateGenerator.putVariable("account", account);

					String link = "https://" + account.getSubdomain()
							+ ".mycollab.com";
					Calendar cal = Calendar.getInstance(TimeZone
							.getTimeZone("UTC"));
					cal.setTime(account.getCreatedtime());
					cal.add(Calendar.DATE, NUM_DAY_FREE_TRIAL);

					templateGenerator.putVariable("expireDay",
							df.format(cal.getTime()));
					templateGenerator.putVariable("userName",
							user.getUsername());
					templateGenerator.putVariable("link", link);
					extMailService.sendHTMLMail("noreply@esofthead.com",
							"noreply@esofthead.com",
							Arrays.asList(new MailRecipientField(user
									.getEmail(), user.getDisplayName())), null,
							null, templateGenerator.generateSubjectContent(),
							templateGenerator.generateBodyContent(), null);
				}
			} else if (afterDay == BillingSendingNotificationJobs.DATE_NOTIFY_EXPIRE) {
				for (SimpleUser user : account.getOwners()) {
					log.error(
							"Send mail after 32day for username {} , mail {}",
							user.getUsername(), user.getEmail());
					TemplateGenerator templateGenerator = new TemplateGenerator(
							"Your trial has ended",
							informAccountIsExpiredTemplate);
					templateGenerator.putVariable("account", account);
					templateGenerator.putVariable("userName",
							user.getUsername());
					String link = "https://" + account.getSubdomain()
							+ ".mycollab.com";
					templateGenerator.putVariable("link", link);

					extMailService.sendHTMLMail("noreply@esofthead.com",
							"noreply@esofthead.com",
							Arrays.asList(new MailRecipientField(user
									.getEmail(), user.getDisplayName())), null,
							null, templateGenerator.generateSubjectContent(),
							templateGenerator.generateBodyContent(), null);
				}
			}
		}
	}
}
