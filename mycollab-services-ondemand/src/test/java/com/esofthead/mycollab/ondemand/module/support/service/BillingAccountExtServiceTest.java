package com.esofthead.mycollab.ondemand.module.support.service;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.ondemand.module.support.domain.SimpleBillingAccount;
import com.esofthead.mycollab.ondemand.module.support.domain.criteria.BillingAccountSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.service.IntergrationServiceTest;
import com.google.common.base.Predicate;
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
public class BillingAccountExtServiceTest extends IntergrationServiceTest {
    @Autowired
    private BillingAccountExtService billingAccountExtService;

    @Test
    @DataSet
    public void testFindAccounts() {
        List<SimpleBillingAccount> billingAccounts = billingAccountExtService.findPagableListByCriteria(
                new SearchRequest<>(new BillingAccountSearchCriteria()));
        assertThat(billingAccounts).hasSize(2);
        Collection<SimpleBillingAccount> filter = Collections2.filter(billingAccounts, new Predicate<SimpleBillingAccount>() {
            @Override
            public boolean apply(SimpleBillingAccount account) {
                return (account.getId() == 1);
            }
        });
        SimpleBillingAccount account = filter.iterator().next();
        assertThat(account).extracting("numProjects", "numUsers", "lastAccessTime").containsSequence(2, 2, new
                LocalDate(2016, 1, 1).toDate());
    }
}
