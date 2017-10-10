package com.mycollab.ondemand.module.support.service

import com.mycollab.db.arguments.DateSearchField
import com.mycollab.db.arguments.BasicSearchRequest
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.ondemand.module.billing.domain.criteria.BillingAccountSearchCriteria
import com.mycollab.ondemand.module.billing.service.BillingService
import com.mycollab.ondemand.test.spring.IntegrationServiceTest
import com.mycollab.test.DataSet
import com.google.common.collect.Collections2
import org.joda.time.LocalDate
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import org.assertj.core.api.Assertions.assertThat

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
@RunWith(SpringJUnit4ClassRunner::class)
class BillingAccountExtServiceTest : IntegrationServiceTest() {
    @Autowired
    private lateinit var billingService: BillingService

    @Test
    @DataSet
    fun testFindAccounts() {
        val criteria = BillingAccountSearchCriteria()
        criteria.statuses = SetSearchField("Active")
        criteria.lastAccessTime = DateSearchField(LocalDate(2016, 1, 3).toDate())
        val billingAccounts = billingService.findPageableListByCriteria(BasicSearchRequest(criteria))
        assertThat(billingAccounts).hasSize(3)

        val filter = Collections2.filter(billingAccounts) { it!!.id == 1 }
        val account = filter.iterator().next()
        assertThat(account).extracting("numProjects", "numUsers", "lastAccessTime").containsSequence(2, 3, LocalDate(2016, 1, 1).toDate())
        val accountOwners = account.accountOwners
        assertThat(accountOwners).hasSize(2).extracting("username").contains("hainguyen@esofthead.com",
                "linhduong@esofthead.com")
    }
}
