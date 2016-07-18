package com.mycollab.schedule.email.user.service;

import com.mycollab.module.billing.AccountReminderStatusContants;
import com.mycollab.module.user.domain.BillingAccount;
import com.mycollab.module.user.domain.BillingAccountWithOwners;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.ondemand.module.billing.service.BillingService;
import com.mycollab.ondemand.module.user.schedule.email.impl.BillingSendingNotificationJob;
import com.mycollab.schedule.email.GenericJobTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.quartz.JobExecutionException;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BillingSendingNotificationJobsTest extends GenericJobTest {

    @InjectMocks
    @Spy
    private BillingSendingNotificationJob billingNotificationJob;

    @Mock
    private BillingService billingService;

    @Test
    public void testSendEmailForAccountExceed25days() throws JobExecutionException {
        BillingAccountWithOwners account = new BillingAccountWithOwners();
        SimpleUser owner = new SimpleUser();
        account.setOwners(Collections.singletonList(owner));

        GregorianCalendar currentTime = new GregorianCalendar();
        currentTime.add(Calendar.DATE, -26);
        account.setCreatedtime(currentTime.getTime());

        List<BillingAccountWithOwners> accounts = Collections.singletonList(account);
        when(billingService.getTrialAccountsWithOwners()).thenReturn(accounts);

        billingNotificationJob.executeJob(context);

        ArgumentCaptor<BillingAccount> billingAccountArgument = ArgumentCaptor.forClass(BillingAccount.class);

        ArgumentCaptor<String> strArgument = ArgumentCaptor.forClass(String.class);

        verify(billingAccountService).updateSelectiveWithSession(
                billingAccountArgument.capture(), strArgument.capture());
        Assert.assertEquals("", strArgument.getValue());
        Assert.assertEquals(
                AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_1ST_TIME,
                billingAccountArgument.getValue().getReminderstatus());
    }

    @Test
    public void testSendEmailForAccountExceed25daysAndAfter29days() throws JobExecutionException {
        BillingAccountWithOwners account = new BillingAccountWithOwners();
        account.setReminderstatus(AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_1ST_TIME);
        SimpleUser owner = new SimpleUser();
        account.setOwners(Collections.singletonList(owner));

        GregorianCalendar currentTime = new GregorianCalendar();
        currentTime.add(Calendar.DATE, -30);
        account.setCreatedtime(currentTime.getTime());

        List<BillingAccountWithOwners> accounts = Collections.singletonList(account);
        when(billingService.getTrialAccountsWithOwners()).thenReturn(accounts);

        billingNotificationJob.executeJob(context);

        ArgumentCaptor<BillingAccount> billingAccountArgument = ArgumentCaptor.forClass(BillingAccount.class);

        ArgumentCaptor<String> strArgument = ArgumentCaptor.forClass(String.class);

        verify(billingAccountService).updateSelectiveWithSession(billingAccountArgument.capture(), strArgument.capture());
        Assert.assertEquals("", strArgument.getValue());
        Assert.assertEquals(AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_2ST_TIME,
                billingAccountArgument.getValue().getReminderstatus());
    }

    @Test
    public void testAccountIsExpireAndConvertToFreePlan() throws JobExecutionException {
        BillingAccountWithOwners account = new BillingAccountWithOwners();
        account.setReminderstatus(AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_2ST_TIME);
        SimpleUser owner = new SimpleUser();
        account.setOwners(Collections.singletonList(owner));

        GregorianCalendar currentTime = new GregorianCalendar();
        currentTime.add(Calendar.DATE, -35);
        account.setCreatedtime(currentTime.getTime());

        List<BillingAccountWithOwners> accounts = Collections.singletonList(account);
        when(billingService.getTrialAccountsWithOwners()).thenReturn(accounts);

//        BillingPlan freePlan = new BillingPlan();
//        freePlan.setId(1);
//        when(billingService.getFreeBillingPlan()).thenReturn(freePlan);

        billingNotificationJob.executeJob(context);

        ArgumentCaptor<BillingAccount> billingAccountArgument = ArgumentCaptor.forClass(BillingAccount.class);

        ArgumentCaptor<String> strArgument = ArgumentCaptor.forClass(String.class);

        verify(billingAccountService).updateSelectiveWithSession(billingAccountArgument.capture(), strArgument.capture());
        Assert.assertEquals("", strArgument.getValue());
        Assert.assertEquals(
                AccountReminderStatusContants.REMIND_ACCOUNT_IS_CONVERTED_TO_FREE_PLAN,
                billingAccountArgument.getValue().getReminderstatus());
        Assert.assertEquals(new Integer(1), billingAccountArgument.getValue()
                .getBillingplanid());
    }
}
