package com.mycollab.ondemand.schedule.jobs

import com.mycollab.module.file.service.UserAvatarService
import com.mycollab.ondemand.module.billing.dao.BillingAccountMapperExt2
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class DeleteObsoleteUsersJob(private val billingAccountMapperExt2: BillingAccountMapperExt2,
                             private val userAvatarService: UserAvatarService) : GenericQuartzJobBean() {

    @Throws(JobExecutionException::class)
    override fun executeJob(context: JobExecutionContext) {
        val users = billingAccountMapperExt2.findUsersNotBelongToAnyAccount()
        users.forEach {
            LOG.info("Delete obsolete user ${it.username}")
            userAvatarService.removeAvatar(it.username)
        }
        billingAccountMapperExt2.removeUsersNotBelongToAnyAccount()
    }

    companion object {
        val LOG = LoggerFactory.getLogger(DeleteObsoleteUsersJob::class.java)
    }
}