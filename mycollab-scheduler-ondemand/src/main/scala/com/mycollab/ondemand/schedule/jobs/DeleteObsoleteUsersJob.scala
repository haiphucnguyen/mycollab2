package com.mycollab.ondemand.schedule.jobs

import com.mycollab.module.file.service.UserAvatarService
import com.mycollab.ondemand.module.billing.dao.BillingAccountMapperExt2
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.quartz.{JobExecutionContext, JobExecutionException}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class DeleteObsoleteUsersJob extends GenericQuartzJobBean {
  
  @Autowired
  private val billingAccountMapperExt2: BillingAccountMapperExt2 = null
  
  @Autowired
  private val userAvatarService: UserAvatarService = null
  
  @throws(classOf[JobExecutionException]) override protected
  def executeJob(context: JobExecutionContext): Unit = {
    import collection.JavaConverters._
    val users = billingAccountMapperExt2.getUsersNotBelongToAnyAccount().asScala.toList
    for (user <- users) {
      userAvatarService.removeAvatar(user.getUsername)
    }
    billingAccountMapperExt2.removeUsersNotBelongToAnyAccount()
  }
}
