package com.mycollab.ondemand.schedule.jobs

import com.mycollab.common.domain.LiveInstanceExample
import com.mycollab.pro.common.dao.LiveInstanceMapper
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
class DeleteObsoleteLiveInstancesJob(private val liveInstanceMapper: LiveInstanceMapper) : GenericQuartzJobBean() {

    @Throws(JobExecutionException::class)
    override fun executeJob(context: JobExecutionContext) {
        val ex = LiveInstanceExample()
        ex.createCriteria().andLastupdateddateLessThan(LocalDate().minusDays(1).toDate())
        liveInstanceMapper.deleteByExample(ex)
    }
}