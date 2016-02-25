package com.esofthead.mycollab.ondemand.module.support.service;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.ondemand.module.support.domain.SimpleBillingAccount;
import com.esofthead.mycollab.ondemand.module.support.domain.criteria.BillingAccountSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.service.IntergrationServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
    }
}
