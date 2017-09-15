package com.mycollab.ondemand.schedule.jobs

import com.mycollab.common.domain.LiveInstanceExample
import com.mycollab.pro.common.dao.LiveInstanceMapper
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.joda.time.LocalDate
import org.quartz.{JobExecutionContext, JobExecutionException}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.2.6
  */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class DeleteObsoleteLiveInstancesJob extends GenericQuartzJobBean {
  @Autowired private val liveInstanceMapper: LiveInstanceMapper = null
  
  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext): Unit = {
    val ex = new LiveInstanceExample
    ex.createCriteria().andLastupdateddateLessThan(new LocalDate().minusDays(1).toDate)
    liveInstanceMapper.deleteByExample(ex)
  }
}
