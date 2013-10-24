package com.esofthead.mycollab.schedule.jobs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners;
import com.esofthead.mycollab.module.user.service.BillingAccountService;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class BillingSendingNotificationJobs extends QuartzJobBean {

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
				cal.add(Calendar.DATE, -25);
				Date dateBefore25Day = cal.getTime();
				if (df.format(account.getCreatedtime()).equals(
						df.format(dateBefore25Day))) {
					BillingSendEmailFactory notificationFactory = new BillingSendEmailFactory(
							account, 25);
					notificationFactory.sendingEmail();
					cal.add(Calendar.DATE, 25);
					continue;
				}
				log.debug("Check whether account exceed 30 days to inform him it is the end of day to upgrade account");
				cal.add(Calendar.DATE, -5);
				Date dateBefore30Day = cal.getTime();
				if (df.format(account.getCreatedtime()).equals(
						df.format(dateBefore30Day))) {
					BillingSendEmailFactory notificationFactory = new BillingSendEmailFactory(
							account, 30);
					notificationFactory.sendingEmail();
					cal.add(Calendar.DATE, 30);
					continue;
				}
				log.debug("Check whether account exceed 32 days to convert to basic plan");
				cal.add(Calendar.DATE, -2);
				Date dateBefore32Day = cal.getTime();
				if (df.format(account.getCreatedtime()).equals(
						df.format(dateBefore32Day))) {
					// TODO : down grade to Free , Need remove user_account ??
					BillingAccount billingAccount = billingAccountService
							.findByPrimaryKey(account.getId(), account.getId());
					// ---Seem BillingPlan Table fix, so hard code is work.
					billingAccount.setBillingplanid(1);
					billingAccountService.updateWithSession(billingAccount, "");
					// ------------------------------------------------------
					BillingSendEmailFactory notificationFactory = new BillingSendEmailFactory(
							account, 32);
					notificationFactory.sendingEmail();
					cal.add(Calendar.DATE, 32);
					continue;
				}
				cal.add(Calendar.DATE, 32);
			}
		}

	}
}
