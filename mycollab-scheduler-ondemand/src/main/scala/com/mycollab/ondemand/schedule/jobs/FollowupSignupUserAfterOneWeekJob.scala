package com.mycollab.ondemand.schedule.jobs

import java.util.Arrays

import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.IDeploymentMode
import com.mycollab.db.arguments.{BasicSearchRequest, RangeDateSearchField, SetSearchField}
import com.mycollab.module.billing.AccountStatusConstants
import com.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.mycollab.ondemand.module.billing.domain.criteria.BillingAccountSearchCriteria
import com.mycollab.ondemand.module.billing.service.BillingService
import com.mycollab.ondemand.module.support.SupportLinkGenerator
import com.mycollab.schedule.jobs.GenericQuartzJobBean
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
  @Autowired private val contentGenerator: IContentGenerator = null
  @Autowired private val billingService: BillingService = null
  @Autowired private val extMailService: ExtMailService = null
  @Autowired private val deploymentMode: IDeploymentMode = null

  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext): Unit = {
    val searchCriteria = new BillingAccountSearchCriteria
    val now = new DateTime()
    searchCriteria.setRegisterTimeDuration(new RangeDateSearchField(now.minusDays(7).toDate, now.minusDays(6).toDate))
    searchCriteria.setStatuses(new SetSearchField[String](AccountStatusConstants.TRIAL))
    import collection.JavaConverters._
    val accounts = billingService.findPageableListByCriteria(new
        BasicSearchRequest[BillingAccountSearchCriteria](searchCriteria)).asScala
    for (account <- accounts) {
      val accountOwners = account.getAccountOwners.asScala
      for (accountOwner <- accountOwners
           if accountOwner.getCanSendEmail) {
        val leadName = accountOwner.getFirstname + " " + accountOwner.getLastname
        contentGenerator.putVariable("lead", leadName)
        contentGenerator.putVariable("unsubscribeUrl",
          SupportLinkGenerator.generateUnsubscribeEmailFullLink(deploymentMode.getSiteUrl("settings"),
            accountOwner.getEmail))
        extMailService.sendHTMLMail("john.adam@mycollab.com", "John Adams",
          Arrays.asList(new MailRecipientField(accountOwner.getEmail, leadName)),
          "How are things going with MyCollab?",
          contentGenerator.parseFile("mailFollowupSignupUserAfter1Week.ftl"))
      }
    }
  }
}
