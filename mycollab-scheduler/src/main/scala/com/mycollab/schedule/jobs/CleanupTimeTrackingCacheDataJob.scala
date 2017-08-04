package com.mycollab.schedule.jobs

import com.mycollab.common.dao.TimelineTrackingCachingMapper
import com.mycollab.common.domain.TimelineTrackingCachingExample
import org.joda.time.LocalDate
import org.quartz.JobExecutionContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.{Profile, Scope}
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.2.2
  */
@Component
@Profile(Array("production"))
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class CleanupTimeTrackingCacheDataJob extends GenericQuartzJobBean {
  @Autowired
  private val timeTrackingCacheMapper: TimelineTrackingCachingMapper = null

  def executeJob(context: JobExecutionContext): Unit = {
    val ex = new TimelineTrackingCachingExample
    val lessThan40Days = new LocalDate().minusDays(40)
    ex.createCriteria().andFordayLessThan(lessThan40Days.toDate)
    timeTrackingCacheMapper.deleteByExample(ex)
  }
}
