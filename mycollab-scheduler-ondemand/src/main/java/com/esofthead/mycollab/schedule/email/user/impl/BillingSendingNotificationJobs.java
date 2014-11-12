/**
 * This file is part of mycollab-scheduler.
 *
 * mycollab-scheduler is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-scheduler is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-scheduler.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.schedule.email.user.impl;

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
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.GenericLinkUtils;
import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.i18n.LocalizationHelper;
import com.esofthead.mycollab.module.billing.AccountReminderStatusContants;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.mail.IContentGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import com.esofthead.mycollab.schedule.jobs.GenericQuartzJobBean;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BillingSendingNotificationJobs extends GenericQuartzJobBean {

	private static final Logger LOG = LoggerFactory
			.getLogger(BillingSendingNotificationJobs.class);

	private static final Integer DATE_REMIND_FOR_FREEPLAN_1ST = 54;
	private static final Integer DATE_REMIND_FOR_FREEPLAN_2ND = 59;
	private static final Integer DATE_NOTIFY_EXPIRE = 62;
	private static final Integer NUM_DAY_FREE_TRIAL = 60;

	static final String INFORM_EXPIRE_ACCOUNT_TEMPLATE = "templates/email/billing/informAccountIsExpiredNotification.mt";
	static final String INFORM_FILLING_BILLING_INFORMATION_TEMPLATE = "templates/email/billing/remindAccountIsAboutExpiredNotification.mt";

	@Autowired
	private BillingService billingService;

	@Autowired
	private BillingAccountService billingAccountService;

	@Autowired
	private ExtMailService extMailService;

	@Autowired
	private IContentGenerator contentGenerator;

	@Override
	protected void executeJob(JobExecutionContext context)
			throws JobExecutionException {

		List<BillingAccountWithOwners> trialAccountsWithOwners = billingService
				.getTrialAccountsWithOwners();

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.DATE, (-1) * DATE_REMIND_FOR_FREEPLAN_1ST);
		Date dateRemind1st = DateTimeUtils.trimHMSOfDate(cal.getTime());

		cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.DATE, (-1) * DATE_REMIND_FOR_FREEPLAN_2ND);
		Date dateRemind2nd = DateTimeUtils.trimHMSOfDate(cal.getTime());

		cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.DATE, (-1) * DATE_NOTIFY_EXPIRE);
		Date dateExpire = DateTimeUtils.trimHMSOfDate(cal.getTime());

		if (trialAccountsWithOwners != null) {
			for (BillingAccountWithOwners account : trialAccountsWithOwners) {
				LOG.debug("Check whether account exceed 25 days to remind user upgrade account");
				Date accCreatedDate = DateTimeUtils.trimHMSOfDate(account
						.getCreatedtime());

				if (accCreatedDate.before(dateRemind1st)
						&& (account.getReminderstatus() == null)) {
					sendRemindEmailAskUpdateBillingAccount(account,
							DATE_REMIND_FOR_FREEPLAN_1ST);
					// update billing account reminder status

					BillingAccount billingAccount = new BillingAccount();
					billingAccount.setId(account.getId());
					billingAccount
							.setReminderstatus(AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_1ST_TIME);
					billingAccountService.updateSelectiveWithSession(
							billingAccount, "");
				} else if (accCreatedDate.before(dateRemind2nd)
						&& (account.getReminderstatus() == AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_1ST_TIME || account
								.getReminderstatus() == null)) {
					LOG.debug("Check whether account exceed 30 days to inform him it is the end of day to upgrade account");
					sendRemindEmailAskUpdateBillingAccount(account,
							DATE_REMIND_FOR_FREEPLAN_2ND);

					BillingAccount billingAccount = new BillingAccount();
					billingAccount.setId(account.getId());
					billingAccount
							.setReminderstatus(AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_2ST_TIME);
					billingAccountService.updateSelectiveWithSession(
							billingAccount, "");
				} else if (accCreatedDate.before(dateExpire)) {
					LOG.debug("Check whether account exceed 32 days to convert to basic plan");
					sendingEmailInformConvertToFreePlan(account);

					BillingAccount billingAccount = new BillingAccount();
					billingAccount.setId(account.getId());
					BillingPlan freeBillingPlan = billingService
							.getFreeBillingPlan();
					billingAccount.setBillingplanid(freeBillingPlan.getId());

					billingAccount
							.setReminderstatus(AccountReminderStatusContants.REMIND_ACCOUNT_IS_CONVERTED_TO_FREE_PLAN);
					billingAccountService.updateSelectiveWithSession(
							billingAccount, "");
				}
			}
		}
	}

	private void sendingEmailInformConvertToFreePlan(
			BillingAccountWithOwners account) {

		for (SimpleUser user : account.getOwners()) {
			LOG.info("Send mail after 32 days for username {} , mail {}",
					user.getUsername(), user.getEmail());
			contentGenerator.putVariable("account", account);
			contentGenerator.putVariable("userName", user.getUsername());
			String link = GenericLinkUtils.generateSiteUrlByAccountId(account
					.getId())
					+ GenericLinkUtils.URL_PREFIX_PARAM
					+ "account/billing";
			contentGenerator.putVariable("link", link);

			extMailService
					.sendHTMLMail(
							SiteConfiguration.getNoReplyEmail(),
							SiteConfiguration.getSiteName(),
							Arrays.asList(new MailRecipientField(user
									.getEmail(), user.getDisplayName())),
							null,
							null,
							contentGenerator
									.generateSubjectContent("Your trial has been ended"),
							contentGenerator.generateBodyContent(
									INFORM_EXPIRE_ACCOUNT_TEMPLATE,
									SiteConfiguration.getDefaultLocale()), null);

		}
	}

	private void sendRemindEmailAskUpdateBillingAccount(
			BillingAccountWithOwners account, Integer afterDay) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

		for (SimpleUser user : account.getOwners()) {
			LOG.info("Send mail after " + afterDay
					+ "days for username {} , mail {}", user.getUsername(),
					user.getEmail());

			contentGenerator.putVariable("account", account);

			String link = GenericLinkUtils.generateSiteUrlByAccountId(account
					.getId())
					+ GenericLinkUtils.URL_PREFIX_PARAM
					+ "account/billing";

			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			cal.setTime(account.getCreatedtime());
			cal.add(Calendar.DATE, NUM_DAY_FREE_TRIAL);

			contentGenerator.putVariable("expireDay", df.format(cal.getTime()));
			contentGenerator.putVariable("userName", user.getUsername());
			contentGenerator.putVariable("link", link);

			extMailService
					.sendHTMLMail(
							SiteConfiguration.getNoReplyEmail(),
							SiteConfiguration.getSiteName(),
							Arrays.asList(new MailRecipientField(user
									.getEmail(), user.getDisplayName())),
							null,
							null,
							contentGenerator
									.generateSubjectContent("Your trial is about to end"),
							contentGenerator
									.generateBodyContent(
											INFORM_FILLING_BILLING_INFORMATION_TEMPLATE,
											SiteConfiguration
													.getDefaultLocale()), null);
		}

	}
}
