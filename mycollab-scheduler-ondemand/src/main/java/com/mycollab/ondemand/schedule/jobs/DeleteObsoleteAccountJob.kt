package com.mycollab.ondemand.schedule.jobs

import com.mycollab.db.arguments.BasicSearchRequest
import com.mycollab.db.arguments.DateSearchField
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.module.billing.AccountStatusConstants
import com.mycollab.ondemand.module.billing.domain.criteria.BillingAccountSearchCriteria
import com.mycollab.ondemand.module.billing.service.BillingService
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.joda.time.LocalDate
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class DeleteObsoleteAccountJob(private val billingService: BillingService) : GenericQuartzJobBean() {

    @Throws(JobExecutionException::class)
    override fun executeJob(context: JobExecutionContext) {
        val searchCriteria = BillingAccountSearchCriteria()
        searchCriteria.statuses = SetSearchField(AccountStatusConstants.TRIAL)
        searchCriteria.lastAccessTime = DateSearchField(LocalDate().minusDays(30).toDate())

//        val obsoleteAccounts = billingService.findPageableListByCriteria(BasicSearchRequest(searchCriteria))
//        obsoleteAccounts.forEach { billingService.cancelAccount(it.id, null) }
    }
}