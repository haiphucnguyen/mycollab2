package com.mycollab.ondemand.module.billing.service

import com.mycollab.core.UserInvalidInputException
import com.mycollab.ondemand.module.billing.ExistedSubDomainException
import com.mycollab.ondemand.test.spring.IntegrationServiceTest
import com.mycollab.test.DataSet
import com.mycollab.test.rule.DbUnitInitializerRule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class, DbUnitInitializerRule::class)
class BillingServiceTest : IntegrationServiceTest() {

    @Autowired
    private lateinit var billingService: BillingService

    @Test
    @DataSet
    fun testGetTrialAccountsWithOwners() {
        val accounts = billingService.trialAccountsWithOwners
        assertThat(accounts.size).isEqualTo(1)

        val account = accounts[0]

        assertThat(account.owners.size).isEqualTo(1)
        val user = account.owners[0]
        assertThat(user.username).isEqualTo("hainguyen@mycollab.com")
    }

    @Test
    @DataSet
    fun registerAccountFailedBecauseDomainIsNotAsciiString() {

        Assertions.assertThrows(UserInvalidInputException::class.java) {
            billingService.registerAccount("ANguyễnHai", 1, "hainguyen@mycollab.com", "123", "hainguyen@mycollab.com", "3", false)
        }
    }

    @Test
    @DataSet
    fun registerTheExistingUsernameAndDifferentPassword() {
        Assertions.assertThrows(UserInvalidInputException::class.java) {
            billingService.registerAccount("xyz", 1, "hainguyen@mycollab.com", "abc", "hainguyen@mycollab.com", "3", false);
            "There is already user hainguyen@mycollab.com in the MyCollab database. If it is yours, you must enter the same password you registered to MyCollab. Otherwise you must use the different email."
        }
    }


    @Test
    @DataSet
    fun registerAccountFailedBecauseSubDomainExisted() {
        Assertions.assertThrows(ExistedSubDomainException::class.java) {
            billingService.registerAccount("abc", 1, "haiphucnguyen@gmail.com", "123", "haiphucnguyen@gmail.com", "3", false)
        }
    }
}
