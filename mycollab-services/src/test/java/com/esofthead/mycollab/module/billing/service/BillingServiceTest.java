package com.esofthead.mycollab.module.billing.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
public class BillingServiceTest extends ServiceTest {

	@Autowired
	private BillingService billingService;

	@Test
	@DataSet
	public void testGetTrialAccountsWithOwners() {
		List<BillingAccountWithOwners> accounts = billingService
				.getTrialAccountsWithOwners();

		Assert.assertEquals(1, accounts.size());

		BillingAccountWithOwners account = accounts.get(0);
		Assert.assertEquals(1, account.getOwners().size());

		SimpleUser user = account.getOwners().get(0);
		Assert.assertEquals("hai79", user.getUsername());
	}
}
