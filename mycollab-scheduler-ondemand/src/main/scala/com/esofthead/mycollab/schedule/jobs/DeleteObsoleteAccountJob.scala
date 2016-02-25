package com.esofthead.mycollab.schedule.jobs

import com.esofthead.mycollab.core.arguments.SearchRequest
import com.esofthead.mycollab.module.billing.esb.DeleteAccountEvent
import com.esofthead.mycollab.module.user.dao.BillingAccountMapper
import com.esofthead.mycollab.ondemand.module.support.domain.criteria.BillingAccountSearchCriteria
import com.esofthead.mycollab.ondemand.module.support.service.BillingAccountExtService
import com.google.common.eventbus.AsyncEventBus
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

  @Autowired private val billingAccountMapper: BillingAccountMapper = null
  @Autowired private val billingAccountExtService: BillingAccountExtService = null
  @Autowired private val asyncEventBus: AsyncEventBus = null

  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext): Unit = {
    val searchCriteria = new BillingAccountSearchCriteria
    import scala.collection.JavaConverters._
    val obsoleteAccounts = billingAccountExtService.findPagableListByCriteria(new
        SearchRequest[BillingAccountSearchCriteria](searchCriteria, 0, Integer.MAX_VALUE)).asScala.toList
    for (obsoleteAccount <- obsoleteAccounts) {
//      val deleteAccountEvent = new DeleteAccountEvent(obsoleteAccount, null)
      System.out.println("ACCOUNT: " + obsoleteAccount)
    }
  }
}
