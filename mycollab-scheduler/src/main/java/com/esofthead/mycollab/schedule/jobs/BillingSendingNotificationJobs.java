package com.esofthead.mycollab.schedule.jobs;

import java.util.List;

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
import com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class BillingSendingNotificationJobs extends QuartzJobBean {

	private static Logger log = LoggerFactory
			.getLogger(BillingSendingNotificationJobs.class);

	@Autowired
	private BillingService billingService;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {

		List<BillingAccountWithOwners> trialAccountsWithOwners = billingService
				.getTrialAccountsWithOwners();

		if (trialAccountsWithOwners != null
				&& trialAccountsWithOwners.size() > 0) {

		}

	}

}
