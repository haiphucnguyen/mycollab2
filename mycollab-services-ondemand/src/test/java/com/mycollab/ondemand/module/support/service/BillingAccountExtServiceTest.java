package com.mycollab.ondemand.module.support.service;

import com.mycollab.db.arguments.DateSearchField;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.ondemand.module.billing.domain.SimpleBillingAccount2;
import com.mycollab.ondemand.module.billing.domain.criteria.BillingAccountSearchCriteria;
import com.mycollab.ondemand.module.billing.service.BillingService;
import com.mycollab.test.DataSet;
import com.mycollab.test.spring.IntegrationServiceTest;
import com.google.common.collect.Collections2;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class BillingAccountExtServiceTest extends IntegrationServiceTest {
    @Autowired
    private BillingService billingService;

    @Test
    @DataSet
    public void testFindAccounts() {
        BillingAccountSearchCriteria criteria = new BillingAccountSearchCriteria();
        criteria.setStatuses(new SetSearchField<>("Active"));
        criteria.setLastAccessTime(new DateSearchField(new LocalDate(2016, 1, 3).toDate()));
        List<SimpleBillingAccount2> billingAccounts = billingService.findPageableListByCriteria(
                new BasicSearchRequest<>(criteria));
        assertThat(billingAccounts).hasSize(3);

        Collection<SimpleBillingAccount2> filter = Collections2.filter(billingAccounts, account -> (account.getId() == 1));
        SimpleBillingAccount2 account = filter.iterator().next();
        assertThat(account).extracting("numProjects", "numUsers", "lastAccessTime").containsSequence(2, 3, new
                LocalDate(2016, 1, 1).toDate());
        List accountOwners = account.getAccountOwners();
        assertThat(accountOwners).hasSize(2).extracting("username").contains("hainguyen@esofthead.com",
                "linhduong@esofthead.com");
    }
}
