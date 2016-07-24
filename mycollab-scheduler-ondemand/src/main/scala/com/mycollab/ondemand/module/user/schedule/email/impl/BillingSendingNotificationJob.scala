package com.mycollab.ondemand.module.user.schedule.email.impl

import java.text.{DateFormat, SimpleDateFormat}
import java.util.{Arrays, Calendar, Locale, TimeZone}

import com.mycollab.common.GenericLinkUtils
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.module.billing.AccountReminderStatusContants
import com.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.mycollab.module.user.domain.{BillingAccount, BillingAccountWithOwners}
import com.mycollab.module.user.service.BillingAccountService
import com.mycollab.ondemand.module.billing.service.BillingService
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.joda.time.LocalDateTime
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
  
  @Autowired private val billingService: BillingService = null
  @Autowired private val billingAccountService: BillingAccountService = null
  @Autowired private val extMailService: ExtMailService = null
  @Autowired private val contentGenerator: IContentGenerator = null
  
  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext) {
    val now = new LocalDateTime()
    val dateRemind1st = now.minusDays(DATE_REMIND_FOR_ENDING_TRIAL_1ST)
    val dateRemind2nd = now.minusDays(DATE_REMIND_FOR_ENDING_TRIAL_2ND)
    val dateExpire = now.minusDays(DATE_NOTIFY_EXPIRE)
  
    import scala.collection.JavaConverters._
    val trialAccountsWithOwners = billingService.getTrialAccountsWithOwners.asScala.toList
    if (trialAccountsWithOwners != null) {
      for (account <- trialAccountsWithOwners) {
        LOG.debug("Check whether account exceed 25 days to remind user upgrade account")
        val accCreatedDate = new LocalDateTime(account.getCreatedtime)
        if (accCreatedDate.isBefore(dateRemind1st) && (account.getReminderstatus == null)) {
          sendRemindEmailAskUpdateBillingAccount(account, DATE_REMIND_FOR_ENDING_TRIAL_1ST)
          val billingAccount = new BillingAccount
          billingAccount.setId(account.getId)
          billingAccount.setReminderstatus(AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_1ST_TIME)
          billingAccountService.updateSelectiveWithSession(billingAccount, "")
        } else if (accCreatedDate.isBefore(dateRemind2nd) &&
          ((account.getReminderstatus eq AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_1ST_TIME)
            || account.getReminderstatus == null)) {
          LOG.debug("Check whether account exceed 30 days to inform him it is the end of day to upgrade account")
          sendRemindEmailAskUpdateBillingAccount(account, DATE_REMIND_FOR_ENDING_TRIAL_2ND)
          val billingAccount: BillingAccount = new BillingAccount
          billingAccount.setId(account.getId)
          billingAccount.setReminderstatus(AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_2ST_TIME)
          billingAccountService.updateSelectiveWithSession(billingAccount, "")
        } else if (accCreatedDate.isBefore(dateExpire)) {
          LOG.debug("Check whether account exceed 32 days to convert to basic plan")
          sendingEmailInformAccountIsRemoved(account)
          val billingAccount = new BillingAccount
          billingAccount.setId(account.getId)
          // Remove the account
        }
      }
    }
  }
  
  private def sendingEmailInformAccountIsRemoved(account: BillingAccountWithOwners) {
    import scala.collection.JavaConversions._
    for (user <- account.getOwners) {
      LOG.info("Send mail after 32 days for username {} , mail {}", Array(user.getUsername, user.getEmail))
      contentGenerator.putVariable("account", account)
      contentGenerator.putVariable("userName", user.getUsername)
      val link = GenericLinkUtils.generateSiteUrlByAccountId(account.getId) + GenericLinkUtils.URL_PREFIX_PARAM + "account/billing"
      contentGenerator.putVariable("link", link)
      System.out.println(contentGenerator.parseFile("mailInformAccountIsExpiredNotification.ftl", Locale.US))
      extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName,
        Arrays.asList(new MailRecipientField(user.getEmail, user.getDisplayName)),
        null, null, "Your trial has expired",
        contentGenerator.parseFile("mailInformAccountIsExpiredNotification.ftl", Locale.US), null)
    }
  }
  
  private def sendRemindEmailAskUpdateBillingAccount(account: BillingAccountWithOwners, afterDay: Integer) {
    val df: DateFormat = new SimpleDateFormat("MM/dd/yyyy")
    import scala.collection.JavaConversions._
    for (user <- account.getOwners) {
      LOG.info("Send mail after " + afterDay + "days for username {} , mail {}", Array(user.getUsername, user.getEmail))
      contentGenerator.putVariable("account", account)
      val link = GenericLinkUtils.generateSiteUrlByAccountId(account.getId) + GenericLinkUtils.URL_PREFIX_PARAM + "account/billing"
      val cal: Calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
      cal.setTime(account.getCreatedtime)
      cal.add(Calendar.DATE, NUM_DAY_FREE_TRIAL)
      contentGenerator.putVariable("expireDay", df.format(cal.getTime))
      contentGenerator.putVariable("userName", user.getUsername)
      contentGenerator.putVariable("link", link)
      extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName,
        Arrays.asList(new MailRecipientField(user.getEmail, user.getDisplayName)), null, null,
        "Your trial will end soon",
        contentGenerator.parseFile("mailRemindAccountIsAboutExpiredNotification.ftl", Locale.US), null)
    }
  }
}
