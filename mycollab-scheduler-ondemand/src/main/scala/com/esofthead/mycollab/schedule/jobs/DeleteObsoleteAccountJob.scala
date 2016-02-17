package com.esofthead.mycollab.schedule.jobs

import com.esofthead.mycollab.module.billing.AccountStatusConstants
import com.esofthead.mycollab.module.billing.esb.DeleteAccountEvent
import com.esofthead.mycollab.module.user.dao.BillingAccountMapper
import com.esofthead.mycollab.module.user.domain.BillingAccountExample
import com.google.common.eventbus.AsyncEventBus
import org.joda.time.LocalDateTime
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
  @Autowired private val asyncEventBus: AsyncEventBus = null

  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext): Unit = {
//    val billingAccountExample = new BillingAccountExample
//    billingAccountExample.createCriteria().andCreatedtimeLessThan(new LocalDateTime().minusDays(60).toDate).
//      andStatusEqualTo(AccountStatusConstants.TRIAL)
//
//    import scala.collection.JavaConverters._
//    val obsoleteAccounts = billingAccountMapper.selectByExample(billingAccountExample).asScala.toList
//    for (obsoleteAccount <- obsoleteAccounts) {
//      val deleteAccountEvent = new DeleteAccountEvent(obsoleteAccount.getId, null)
//      asyncEventBus.post(deleteAccountEvent)
//    }
//    billingAccountMapper.deleteByExample(billingAccountExample)
  }
}
