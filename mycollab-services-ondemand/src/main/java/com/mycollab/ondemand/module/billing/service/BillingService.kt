package com.mycollab.ondemand.module.billing.service

import com.mycollab.common.domain.CustomerFeedbackWithBLOBs
import com.mycollab.core.cache.CacheEvict
import com.mycollab.core.cache.CacheKey
import com.mycollab.core.cache.Cacheable
import com.mycollab.db.arguments.BasicSearchRequest
import com.mycollab.db.persistence.service.IService
import com.mycollab.module.user.domain.BillingAccountWithOwners
import com.mycollab.module.user.domain.BillingPlan
import com.mycollab.ondemand.module.billing.domain.SimpleBillingAccount2
import com.mycollab.ondemand.module.billing.domain.criteria.BillingAccountSearchCriteria

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
interface BillingService : IService {

    val availablePlans: List<BillingPlan>

    val trialAccountsWithOwners: List<BillingAccountWithOwners>
    fun findPageableListByCriteria(searchRequest: BasicSearchRequest<BillingAccountSearchCriteria>): List<SimpleBillingAccount2>

    fun registerAccount(subDomain: String, billingPlanId: Int, username: String,
                        password: String, email: String, timezoneId: String,
                        isEmailVerified: Boolean)

    fun cancelAccount(accountId: Int, feedback: CustomerFeedbackWithBLOBs?)

    @CacheEvict
    fun updateBillingPlan(@CacheKey accountId: Int, oldPlan: BillingPlan, newPlan: BillingPlan)

    fun getSubDomainsOfUser(username: String): List<String>

    @Cacheable
    fun findBillingPlan(@CacheKey sAccountId: Int): BillingPlan?
}
