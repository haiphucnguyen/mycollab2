package com.esofthead.mycollab.schedule.jobs

import com.esofthead.mycollab.common.dao.LiveInstanceMapper
import com.esofthead.mycollab.common.domain.LiveInstanceExample
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
    ex.createCriteria().andLastupdateddateLessThan(new LocalDate().minusDays(30).toDate)
    liveInstanceMapper.deleteByExample(ex)
  }
}
