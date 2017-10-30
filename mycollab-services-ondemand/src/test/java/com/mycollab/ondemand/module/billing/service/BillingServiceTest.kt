package com.mycollab.ondemand.module.billing.service

import com.mycollab.core.UserInvalidInputException
import com.mycollab.ondemand.module.billing.ExistedSubDomainException
import com.mycollab.ondemand.test.spring.IntegrationServiceTest
import com.mycollab.test.DataSet
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
class BillingServiceTest : IntegrationServiceTest() {

    @Autowired
    private lateinit var billingService: BillingService

    @Rule
    var expectedEx = ExpectedException.none()

    @Test
    @DataSet
    fun testGetTrialAccountsWithOwners() {
        val accounts = billingService.trialAccountsWithOwners
        assertThat(accounts.size).isEqualTo(1)

        val account = accounts[0]

        assertThat(account.owners.size).isEqualTo(1)
        val user = account.owners[0]
        assertThat(user.username).isEqualTo("hainguyen@esofthead.com")
    }

    @Test
    @DataSet
    fun registerAccountFailedBecauseDomainIsNotAsciiString() {
        expectedEx.expect(UserInvalidInputException::class.java)

        billingService.registerAccount("ANguyễnHai", 1, "hainguyen@esofthead.com", "123", "hainguyen@esofthead.com", "3", false)
    }

    @Test
    @DataSet
    fun registerTheExistingUsernameAndDifferentPassword() {
        expectedEx.expect(UserInvalidInputException::class.java)
        expectedEx.expectMessage("There is already user hainguyen@esofthead.com in the MyCollab database. If it is yours, you must enter the same password you registered to MyCollab. Otherwise you must use the different email.")
        billingService.registerAccount("xyz", 1, "hainguyen@esofthead.com",
                "abc", "hainguyen@esofthead.com", "3", false)
    }

    @Test(expected = ExistedSubDomainException::class)
    @DataSet
    fun registerAccountFailedBecauseSubDomainExisted() {
        billingService.registerAccount("abc", 1, "haiphucnguyen@gmail.com", "123", "haiphucnguyen@gmail.com", "3", false)
    }
}
