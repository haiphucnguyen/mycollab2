package com.mycollab.schedule.jobs

import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.slf4j.LoggerFactory
import org.springframework.dao.TransientDataAccessException
import org.springframework.scheduling.quartz.QuartzJobBean

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
abstract class GenericQuartzJobBean : QuartzJobBean() {
    companion object {
        private val LOG = LoggerFactory.getLogger(GenericQuartzJobBean::class.java)
        private var isSendingDbError = false
    }

    @Throws(JobExecutionException::class)
    abstract fun executeJob(context: JobExecutionContext)

    @Throws(JobExecutionException::class)
    override fun executeInternal(context: JobExecutionContext) {
        try {
            executeJob(context)
            GenericQuartzJobBean.isSendingDbError = false
        } catch (e: TransientDataAccessException) {
            if (!GenericQuartzJobBean.isSendingDbError) {
                LOG.error("Exception in running schedule", e)
                GenericQuartzJobBean.isSendingDbError = true
            }
        } catch (e: Exception) {
            LOG.error("Exception in running schedule", e)
        }
    }
}

