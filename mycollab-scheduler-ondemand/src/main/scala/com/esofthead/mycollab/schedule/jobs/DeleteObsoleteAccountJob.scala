package com.esofthead.mycollab.schedule.jobs

import com.esofthead.mycollab.module.user.dao.BillingAccountMapper
import com.esofthead.mycollab.module.user.domain.BillingAccountExample
import org.joda.time.LocalDateTime
import org.quartz.{JobExecutionContext, JobExecutionException}
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.2.6
  */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class DeleteObsoleteAccountJob extends GenericQuartzJobBean {

  private val billingAccountMapper: BillingAccountMapper = null

  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext): Unit = {
    val billingAccountExample = new BillingAccountExample
    billingAccountExample.createCriteria().andCreatedtimeLessThan(new LocalDateTime().minusDays(60).toDate).andStatusNotEqualTo("")
  }
}
