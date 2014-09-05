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
package com.esofthead.mycollab.module.billing.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.rest.server.signup.SubdomainExistedException;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
public class BillingServiceTest extends ServiceTest {

	@Autowired
	private BillingService billingService;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

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

	@Test
	@DataSet
	public void registerAccountFailedBecauseDomainIsNotAsciiString() {
		expectedEx.expect(MyCollabException.class);
		expectedEx.expectMessage("Subdomain must be an ascii string");

		billingService.registerAccount("ANguyễnHai", 1,
				"hainguyen@esofthead.com", "123", "hainguyen@esofthead.com",
				"3", false);
	}

	@Test(expected = SubdomainExistedException.class)
	@DataSet
	public void registerAccountFailedBecauseSubDomainExisted() {
		billingService.registerAccount("abc", 1, "haiphucnguyen@gmail.com",
				"123", "haiphucnguyen@gmail.com", "3", false);
	}
}
