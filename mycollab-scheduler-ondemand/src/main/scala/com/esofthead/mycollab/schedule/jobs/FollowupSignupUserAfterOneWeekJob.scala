package com.esofthead.mycollab.schedule.jobs

import java.util.Arrays

import com.esofthead.mycollab.common.domain.MailRecipientField
import com.esofthead.mycollab.core.arguments.{BasicSearchRequest, RangeDateSearchField, SetSearchField}
import com.esofthead.mycollab.module.billing.AccountStatusConstants
import com.esofthead.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.esofthead.mycollab.ondemand.module.support.domain.criteria.BillingAccountSearchCriteria
import com.esofthead.mycollab.ondemand.module.support.service.BillingAccountExtService
import org.joda.time.DateTime
import org.quartz.{JobExecutionContext, JobExecutionException}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.3.4
  */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class FollowupSignupUserAfterOneWeekJob extends GenericQuartzJobBean {
  @Autowired var contentGenerator: IContentGenerator = _
  @Autowired var billingAccountExtService: BillingAccountExtService = _
  @Autowired var extMailService: ExtMailService = _

  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext): Unit = {
    var searchCriteria = new BillingAccountSearchCriteria
    var now = new DateTime()
    searchCriteria.setRegisterTimeDuration(new RangeDateSearchField(now.minusDays(7).toDate, now.minusDays(6).toDate))
    searchCriteria.setStatuses(new SetSearchField[String](AccountStatusConstants.TRIAL))
    import collection.JavaConverters._
    val accounts = billingAccountExtService.findPagableListByCriteria(new
        BasicSearchRequest[BillingAccountSearchCriteria](searchCriteria)).asScala
    for (account <- accounts) {
      val accountOwners = account.getAccountOwners.asScala
      for (accountOwner <- accountOwners) {
        val leadName = accountOwner.getFirstname + " " + accountOwner.getLastname
        contentGenerator.putVariable("lead", leadName)
        extMailService.sendHTMLMail("john.adam@mycollab.com", "John Adams",
          Arrays.asList(new MailRecipientField(accountOwner.getEmail, leadName)),
          null, null, contentGenerator.parseString("How are things going with MyCollab?"),
          contentGenerator.parseFile("mailFollowupSignupUserAfter1Week.html"), null)
      }
    }
  }
}
