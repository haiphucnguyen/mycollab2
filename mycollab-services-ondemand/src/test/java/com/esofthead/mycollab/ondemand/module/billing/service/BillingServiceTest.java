package com.esofthead.mycollab.ondemand.module.billing.service;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.ondemand.module.billing.SubdomainExistedException;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.service.IntergrationServiceTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class BillingServiceTest extends IntergrationServiceTest {

    @Autowired
    private BillingService billingService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    @DataSet
    public void testGetTrialAccountsWithOwners() {
        List<BillingAccountWithOwners> accounts = billingService.getTrialAccountsWithOwners();
        assertThat(accounts.size()).isEqualTo(1);

        BillingAccountWithOwners account = accounts.get(0);

        assertThat(account.getOwners().size()).isEqualTo(1);
        SimpleUser user = account.getOwners().get(0);
        assertThat(user.getUsername()).isEqualTo("hainguyen@esofthead.com");
    }

    @Test
    @DataSet
    public void registerAccountFailedBecauseDomainIsNotAsciiString() {
        expectedEx.expect(MyCollabException.class);
        expectedEx.expectMessage("Subdomain must be an ascii string");

        billingService.registerAccount("ANguyễnHai", 1, "hainguyen@esofthead.com", "123", "hainguyen@esofthead.com", "3", false);
    }

    @Test
    @DataSet
    public void registerTheExistingUsernameAndDifferentPassword() {
        expectedEx.expect(UserInvalidInputException.class);
        expectedEx.expectMessage("There is already user hainguyen@esofthead.com in the MyCollab database. If it is yours, you must enter the same password you registered to MyCollab. Otherwise you must use the different email.");
        billingService.registerAccount("xyz", 1, "hainguyen@esofthead.com",
                "abc", "hainguyen@esofthead.com", "3", false);
    }

    @Test(expected = SubdomainExistedException.class)
    @DataSet
    public void registerAccountFailedBecauseSubDomainExisted() {
        billingService.registerAccount("abc", 1, "haiphucnguyen@gmail.com", "123", "haiphucnguyen@gmail.com", "3", false);
    }
}