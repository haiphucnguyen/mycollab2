package com.mycollab.schedule.jobs

import com.mycollab.common.dao.TimelineTrackingCachingMapper
import com.mycollab.common.domain.TimelineTrackingCachingExample
import org.joda.time.LocalDate
import org.quartz.JobExecutionContext
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Component
@Profile("production")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class CleanupTimeTrackingCacheDataJob(private val timeTrackingCacheMapper: TimelineTrackingCachingMapper) : GenericQuartzJobBean() {

    override fun executeJob(context: JobExecutionContext) {
        val ex = TimelineTrackingCachingExample()
        val lessThan40Days = LocalDate().minusDays(40)
        ex.createCriteria().andFordayLessThan(lessThan40Days.toDate())
        timeTrackingCacheMapper.deleteByExample(ex)
    }
}