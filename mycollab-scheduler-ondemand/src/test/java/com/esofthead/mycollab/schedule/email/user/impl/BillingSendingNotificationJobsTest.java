package com.esofthead.mycollab.schedule.email.user.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.Spy;
import org.quartz.JobExecutionException;

import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.GenericJobTest;

public class BillingSendingNotificationJobsTest extends GenericJobTest {

	@InjectMocks
	@Spy
	private BillingSendingNotificationJobs billingNotificationJob;

	@Mock
	private BillingService billingService;

	@Test
	public void testSendEmailForAccountExceed25days()
			throws JobExecutionException {
		BillingAccountWithOwners account = new BillingAccountWithOwners();
		SimpleUser owner = new SimpleUser();
		account.setOwners(Arrays.asList(owner));

		GregorianCalendar currentTime = new GregorianCalendar();
		currentTime.add(Calendar.DATE, -26);
		account.setCreatedtime(currentTime.getTime());

		List<BillingAccountWithOwners> accounts = Arrays.asList(account);
		when(billingService.getTrialAccountsWithOwners()).thenReturn(accounts);

		billingNotificationJob.executeJob(context);
	}
}
