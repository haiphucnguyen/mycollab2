package com.mycollab.ondemand.module.user.schedule.email.impl

import java.util.{Collections, Locale}

import com.mycollab.common.GenericLinkUtils
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.{IDeploymentMode, SiteConfiguration}
import com.mycollab.module.billing.{AccountReminderStatusContants, AccountStatusConstants, RegisterStatusConstants}
import com.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.mycollab.module.user.domain.{BillingAccount, BillingAccountWithOwners}
import com.mycollab.module.user.service.BillingAccountService
import com.mycollab.ondemand.module.billing.service.BillingService
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import org.quartz.{DisallowConcurrentExecution, JobExecutionContext, JobExecutionException}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@DisallowConcurrentExecution
class BillingSendingNotificationJob extends GenericQuartzJobBean {
  private val LOG: Logger = LoggerFactory.getLogger(classOf[BillingSendingNotificationJob])
  private val DATE_REMIND_FOR_ENDING_TRIAL_1ST: Integer = 24
  private val DATE_REMIND_FOR_ENDING_TRIAL_2ND: Integer = 29
  private val DATE_NOTIFY_EXPIRE: Integer = 35
  private val NUM_DAY_FREE_TRIAL: Integer = 30
  
  @Autowired var billingService: BillingService = _
  @Autowired var billingAccountService: BillingAccountService = _
  @Autowired var extMailService: ExtMailService = _
  @Autowired var contentGenerator: IContentGenerator = _
  @Autowired var deploymentMode: IDeploymentMode = _
  
  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext) {
    val now = new LocalDateTime()
    val dateRemind1st = now.minusDays(DATE_REMIND_FOR_ENDING_TRIAL_1ST)
    val dateRemind2nd = now.minusDays(DATE_REMIND_FOR_ENDING_TRIAL_2ND)
    val dateExpire = now.minusDays(DATE_NOTIFY_EXPIRE)
    
    import scala.collection.JavaConverters._
    val trialAccountsWithOwners = billingService.getTrialAccountsWithOwners.asScala.toList
    
    for (account <- trialAccountsWithOwners) {
      val accCreatedDate = new LocalDateTime(account.getCreatedtime)
      if (accCreatedDate.isBefore(dateRemind1st) && (account.getReminderstatus == null)) {
        sendRemindEmailAskUpdateBillingAccount(account, DATE_REMIND_FOR_ENDING_TRIAL_1ST)
        val billingAccount = new BillingAccount
        billingAccount.setId(account.getId)
        billingAccount.setReminderstatus(AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_1ST_TIME)
        billingAccountService.updateSelectiveWithSession(billingAccount, "")
      } else if (accCreatedDate.isBefore(dateRemind2nd) &&
        (account.getReminderstatus == AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_1ST_TIME)) {
        sendRemindEmailAskUpdateBillingAccount(account, DATE_REMIND_FOR_ENDING_TRIAL_2ND)
        val billingAccount: BillingAccount = new BillingAccount
        billingAccount.setId(account.getId)
        billingAccount.setReminderstatus(AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_2ST_TIME)
        billingAccountService.updateSelectiveWithSession(billingAccount, "")
      } else if (accCreatedDate.isBefore(dateExpire)) {
        LOG.debug("Check whether account exceed 32 days to convert to basic plan")
        val billingAccount: BillingAccount = new BillingAccount
        billingAccount.setId(account.getId)
        billingAccount.setStatus(AccountStatusConstants.INVALID)
        billingAccountService.updateSelectiveWithSession(billingAccount, "")
        sendingEmailInformAccountIsRemoved(account)
      } else {
        LOG.info("Condition here")
      }
    }
  }
  
  private def sendingEmailInformAccountIsRemoved(account: BillingAccountWithOwners) {
    import scala.collection.JavaConversions._
    for (user <- account.getOwners) {
      LOG.info("Send mail after 32 days for username {} , mail {}", Array(user.getUsername, user.getEmail))
      contentGenerator.putVariable("account", account)
      contentGenerator.putVariable("userName", user.getLastname)
      val link = deploymentMode.getSiteUrl(account.getSubdomain) + GenericLinkUtils.URL_PREFIX_PARAM + "account/billing"
      contentGenerator.putVariable("link", link)
      extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName,
        Collections.singletonList(new MailRecipientField(user.getEmail, user.getDisplayName)),
        null, Collections.singletonList(new MailRecipientField("hainguyen@esofthead.com", "Hai Nguyen")), "Your trial has expired",
        contentGenerator.parseFile("mailInformAccountIsExpiredNotification.ftl", Locale.US), null)
    }
  }
  
  private def sendRemindEmailAskUpdateBillingAccount(account: BillingAccountWithOwners, afterDay: Integer) {
    val df = DateTimeFormat.forPattern("MM/dd/yyyy")
    import scala.collection.JavaConversions._
    for (user <- account.getOwners) {
      LOG.info("Send mail after " + afterDay + "days for username {} , mail {}", Array(user.getUsername, user.getEmail))
      contentGenerator.putVariable("account", account)
      val link = deploymentMode.getSiteUrl(account.getSubdomain) + GenericLinkUtils.URL_PREFIX_PARAM + "account/billing"
      val cal = new LocalDateTime(account.getCreatedtime)
      cal.plusDays(NUM_DAY_FREE_TRIAL)
      contentGenerator.putVariable("expireDay", df.print(cal))
      contentGenerator.putVariable("userName", user.getLastname)
      contentGenerator.putVariable("link", link)
      extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName,
        Collections.singletonList(new MailRecipientField(user.getEmail, user.getDisplayName)), null,
        Collections.singletonList(new MailRecipientField("hainguyen@esofthead.com", "Hai Nguyen")),
        "Your trial will end soon",
        contentGenerator.parseFile("mailRemindAccountIsAboutExpiredNotification.ftl", Locale.US), null)
    }
  }
}
