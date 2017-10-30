package com.mycollab.ondemand.module.billing.service.impl

import com.google.common.eventbus.AsyncEventBus
import com.mycollab.common.domain.CustomerFeedbackWithBLOBs
import com.mycollab.common.i18n.ErrorI18nEnum
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.core.MyCollabException
import com.mycollab.core.UserInvalidInputException
import com.mycollab.core.cache.CacheKey
import com.mycollab.db.arguments.BasicSearchRequest
import com.mycollab.i18n.LocalizationHelper
import com.mycollab.module.billing.AccountStatusConstants
import com.mycollab.module.user.dao.BillingAccountMapper
import com.mycollab.module.user.dao.BillingPlanMapper
import com.mycollab.module.user.domain.*
import com.mycollab.module.user.service.BillingAccountService
import com.mycollab.ondemand.module.billing.ExistedSubDomainException
import com.mycollab.ondemand.module.billing.dao.BillingAccountMapperExt2
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionMapper
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionExample
import com.mycollab.ondemand.module.billing.domain.SimpleBillingAccount2
import com.mycollab.ondemand.module.billing.domain.criteria.BillingAccountSearchCriteria
import com.mycollab.ondemand.module.billing.esb.DeleteAccountEvent
import com.mycollab.ondemand.module.billing.esb.DeleteSubscriptionEvent
import com.mycollab.ondemand.module.billing.esb.UpdateBillingPlanEvent
import com.mycollab.ondemand.module.billing.service.BillingService
import org.apache.ibatis.session.RowBounds
import org.joda.time.DateTime
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
@Service
class BillingServiceImpl(private val billingAccountMapperExt2: BillingAccountMapperExt2,
                         private val billingPlanMapper: BillingPlanMapper,
                         private val billingAccountMapper: BillingAccountMapper,
                         private val billingAccountService: BillingAccountService,
                         private val billingSubscriptionMapper: BillingSubscriptionMapper,
                         private val asyncEventBus: AsyncEventBus) : BillingService {

    override val availablePlans: List<BillingPlan>
        get() {
            val ex = BillingPlanExample()
            return billingPlanMapper.selectByExample(ex)
        }

    override val trialAccountsWithOwners: List<BillingAccountWithOwners>
        get() = billingAccountMapperExt2.trialAccountsWithOwners

    @Transactional
    override fun registerAccount(subDomain: String, billingPlanId: Int, username: String, password: String, email: String, timezoneId: String,
                                 isEmailVerified: Boolean) {

        // check subDomain is ascii string
        if (!subDomain.matches("\\A[a-zA-Z0-9\\-_]+\\z".toRegex())) {
            throw UserInvalidInputException("Subdomain is not valid. You can use letters (abc), numbers (123), -. Space is not allowed")
        }

        // check subDomain belong to keyword list
        if (ACCOUNT_BLACK_LIST.contains(subDomain)) {
            throw ExistedSubDomainException(LocalizationHelper.getMessage(SiteConfiguration.getDefaultLocale(),
                    ErrorI18nEnum.EXISTING_DOMAIN_REGISTER_ERROR, subDomain))
        }

        val billingEx = BillingAccountExample()
        billingEx.createCriteria().andSubdomainEqualTo(subDomain)
        if (billingAccountMapper.countByExample(billingEx) > 0) {
            throw ExistedSubDomainException(LocalizationHelper.getMessage(SiteConfiguration.getDefaultLocale(),
                    ErrorI18nEnum.EXISTING_DOMAIN_REGISTER_ERROR, subDomain))
        }

        val billingPlan = billingPlanMapper.selectByPrimaryKey(billingPlanId)
        val now = DateTime()
        // Save billing account
        val billingAccount = BillingAccount()
        billingAccount.billingplanid = billingPlan.id
        billingAccount.createdtime = now.toDate()
        billingAccount.trialfrom = now.toDate()
        billingAccount.trialto = now.plusDays(30).toDate()
        billingAccount.status = AccountStatusConstants.TRIAL
        billingAccount.subdomain = subDomain
        billingAccount.displayemailpublicly = true
        billingAccount.defaulttimezone = timezoneId

        try {
            billingAccountMapper.insertAndReturnKey(billingAccount)
        } catch (e: DuplicateKeyException) {
            throw ExistedSubDomainException(LocalizationHelper.getMessage(SiteConfiguration.getDefaultLocale(),
                    ErrorI18nEnum.EXISTING_DOMAIN_REGISTER_ERROR, subDomain))
        }

        val accountId = billingAccount.id!!
        billingAccountService.createDefaultAccountData(username, password, timezoneId, SiteConfiguration.getDefaultLocale().language,
                isEmailVerified, true, accountId)
    }

    override fun cancelAccount(accountId: Int, feedback: CustomerFeedbackWithBLOBs?) {
        val subscriptionExample = BillingSubscriptionExample()
        subscriptionExample.createCriteria().andAccountidEqualTo(accountId)
        val billingSubscriptions = billingSubscriptionMapper.selectByExample(subscriptionExample)
        val deleteSubscriptionEvent = DeleteSubscriptionEvent(billingSubscriptions)
        asyncEventBus.post(deleteSubscriptionEvent)

        billingAccountMapper.deleteByPrimaryKey(accountId)
        val event = DeleteAccountEvent(accountId, feedback)
        asyncEventBus.post(event)
    }

    override fun findPageableListByCriteria(searchRequest: BasicSearchRequest<BillingAccountSearchCriteria>): List<SimpleBillingAccount2> {
        return billingAccountMapperExt2.findPageableListByCriteria(searchRequest.searchCriteria,
                RowBounds((searchRequest.currentPage - 1) * searchRequest.numberOfItems,
                        searchRequest.numberOfItems))
    }

    override fun updateBillingPlan(@CacheKey accountId: Int, oldPlan: BillingPlan, newPlan: BillingPlan) {
        val record = BillingAccount()
        record.id = accountId
        record.billingplanid = newPlan.id
        billingAccountMapper.updateByPrimaryKeySelective(record)

        asyncEventBus.post(UpdateBillingPlanEvent(accountId, newPlan))
    }

    override fun getSubDomainsOfUser(username: String): List<String> =
            this.billingAccountMapperExt2.getSubDomainsOfUser(username)

    override fun findBillingPlan(@CacheKey sAccountId: Int): BillingPlan? {
        val billingAccount = billingAccountService.getBillingAccountById(sAccountId)
        return if (billingAccount != null) {
            billingAccount.billingPlan
        } else {
            throw MyCollabException("Can not find the billing plan for account $sAccountId")
        }
    }

    companion object {
        private val ACCOUNT_BLACK_LIST = Arrays.asList("api", "blog", "forum", "wiki",
                "support", "community", "www")
    }
}
