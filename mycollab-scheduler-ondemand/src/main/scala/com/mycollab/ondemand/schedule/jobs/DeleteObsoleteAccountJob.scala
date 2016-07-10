package com.mycollab.ondemand.schedule.jobs

import com.google.common.eventbus.AsyncEventBus
import com.mycollab.db.arguments.{BasicSearchRequest, DateSearchField, SetSearchField}
import com.mycollab.ondemand.module.billing.domain.criteria.BillingAccountSearchCriteria
import com.mycollab.ondemand.module.billing.esb.DeleteAccountEvent
import com.mycollab.ondemand.module.billing.service.BillingAccountExtService
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
class DeleteObsoleteAccountJob extends GenericQuartzJobBean {
  @Autowired private val billingAccountExtService: BillingAccountExtService = null
  @Autowired private val asyncEventBus: AsyncEventBus = null

  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext): Unit = {
    val searchCriteria = new BillingAccountSearchCriteria
    searchCriteria.setStatuses(new SetSearchField[String]("Trial"))
    searchCriteria.setLastAccessTime(new DateSearchField(new LocalDate().minusDays(60).toDate))
    import collection.JavaConverters._
    val obsoleteAccounts = billingAccountExtService.findPagableListByCriteria(new
        BasicSearchRequest[BillingAccountSearchCriteria](searchCriteria, 0, Integer.MAX_VALUE)).asScala.toList
    for (obsoleteAccount <- obsoleteAccounts) {
      val deleteAccountEvent = new DeleteAccountEvent(obsoleteAccount.getId, null)
      asyncEventBus.post(deleteAccountEvent)
    }
  }
}
