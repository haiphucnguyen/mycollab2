package com.esofthead.mycollab.schedule.email.user.impl

import java.text.{DateFormat, SimpleDateFormat}
import java.util.{Arrays, Calendar, Locale, TimeZone}

import com.esofthead.mycollab.common.GenericLinkUtils
import com.esofthead.mycollab.common.domain.MailRecipientField
import com.esofthead.mycollab.configuration.SiteConfiguration
import com.esofthead.mycollab.module.billing.service.BillingService
import com.esofthead.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners
import com.esofthead.mycollab.module.user.service.BillingAccountService
import com.esofthead.mycollab.schedule.jobs.GenericQuartzJobBean
import org.quartz.{JobExecutionContext, JobExecutionException}
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
class BillingSendingNotificationJob extends GenericQuartzJobBean {
  private val LOG: Logger = LoggerFactory.getLogger(classOf[BillingSendingNotificationJob])
  private val DATE_REMIND_FOR_FREEPLAN_1ST: Integer = 54
  private val DATE_REMIND_FOR_FREEPLAN_2ND: Integer = 59
  private val DATE_NOTIFY_EXPIRE: Integer = 62
  private val NUM_DAY_FREE_TRIAL: Integer = 60
  private val INFORM_EXPIRE_ACCOUNT_TEMPLATE: String = "mailInformAccountIsExpiredNotification.html"
  private val INFORM_FILLING_BILLING_INFORMATION_TEMPLATE: String = "mailRemindAccountIsAboutExpiredNotification.html"

  @Autowired var billingService: BillingService = _
  @Autowired var billingAccountService: BillingAccountService = _
  @Autowired var extMailService: ExtMailService = _
  @Autowired var contentGenerator: IContentGenerator = _

  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext) {
    //    import scala.collection.JavaConverters._
    //    val trialAccountsWithOwners: List[BillingAccountWithOwners] = billingService.getTrialAccountsWithOwners.asScala
    //      .toList
    //    var cal: Calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    //    cal.add(Calendar.DATE, (-1) * DATE_REMIND_FOR_FREEPLAN_1ST)
    //    val dateRemind1st: Date = DateTimeUtils.trimHMSOfDate(cal.getTime)
    //    cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    //    cal.add(Calendar.DATE, (-1) * DATE_REMIND_FOR_FREEPLAN_2ND)
    //    val dateRemind2nd: Date = DateTimeUtils.trimHMSOfDate(cal.getTime)
    //    cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    //    cal.add(Calendar.DATE, (-1) * DATE_NOTIFY_EXPIRE)
    //    val dateExpire: Date = DateTimeUtils.trimHMSOfDate(cal.getTime)
    //    if (trialAccountsWithOwners != null) {
    //      for (account <- trialAccountsWithOwners) {
    //        LOG.debug("Check whether account exceed 25 days to remind user upgrade account")
    //        val accCreatedDate: Date = DateTimeUtils.trimHMSOfDate(account.getCreatedtime)
    //        if (accCreatedDate.before(dateRemind1st) && (account.getReminderstatus == null)) {
    //          sendRemindEmailAskUpdateBillingAccount(account, DATE_REMIND_FOR_FREEPLAN_1ST)
    //          val billingAccount: BillingAccount = new BillingAccount
    //          billingAccount.setId(account.getId)
    //          billingAccount.setReminderstatus(AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_1ST_TIME)
    //          billingAccountService.updateSelectiveWithSession(billingAccount, "")
    //        }
    //        else if (accCreatedDate.before(dateRemind2nd) && ((account.getReminderstatus eq AccountReminderStatusContants
    //          .REMIND_ACCOUNT_IS_ABOUT_END_1ST_TIME) || account.getReminderstatus == null)) {
    //          LOG.debug("Check whether account exceed 30 days to inform him it is the end of day to upgrade account")
    //          sendRemindEmailAskUpdateBillingAccount(account, DATE_REMIND_FOR_FREEPLAN_2ND)
    //          val billingAccount: BillingAccount = new BillingAccount
    //          billingAccount.setId(account.getId)
    //          billingAccount.setReminderstatus(AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_2ST_TIME)
    //          billingAccountService.updateSelectiveWithSession(billingAccount, "")
    //        }
    //        else if (accCreatedDate.before(dateExpire)) {
    //          LOG.debug("Check whether account exceed 32 days to convert to basic plan")
    //          sendingEmailInformConvertToFreePlan(account)
    //          val billingAccount: BillingAccount = new BillingAccount
    //          billingAccount.setId(account.getId)
    //          val freeBillingPlan: BillingPlan = billingService.getFreeBillingPlan
    //          billingAccount.setBillingplanid(freeBillingPlan.getId)
    //          billingAccount.setReminderstatus(AccountReminderStatusContants.REMIND_ACCOUNT_IS_CONVERTED_TO_FREE_PLAN)
    //          billingAccountService.updateSelectiveWithSession(billingAccount, "")
    //        }
    //      }
    //    }
  }

  private def sendingEmailInformConvertToFreePlan(account: BillingAccountWithOwners) {
    import scala.collection.JavaConversions._
    for (user <- account.getOwners) {
      LOG.info("Send mail after 32 days for username {} , mail {}", Array(user.getUsername, user.getEmail))
      contentGenerator.putVariable("account", account)
      contentGenerator.putVariable("userName", user.getUsername)
      val link: String = GenericLinkUtils.generateSiteUrlByAccountId(account.getId) + GenericLinkUtils.URL_PREFIX_PARAM + "account/billing"
      contentGenerator.putVariable("link", link)
      extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName,
        Arrays.asList(new MailRecipientField(user.getEmail, user.getDisplayName)),
        null, null, contentGenerator.parseString("Your trial has been ended"),
        contentGenerator.parseFile(INFORM_EXPIRE_ACCOUNT_TEMPLATE, Locale.US), null)
    }
  }

  private def sendRemindEmailAskUpdateBillingAccount(account: BillingAccountWithOwners, afterDay: Integer) {
    val df: DateFormat = new SimpleDateFormat("MM/dd/yyyy")
    import scala.collection.JavaConversions._
    for (user <- account.getOwners) {
      LOG.info("Send mail after " + afterDay + "days for username {} , mail {}", Array(user.getUsername, user.getEmail))
      contentGenerator.putVariable("account", account)
      val link: String = GenericLinkUtils.generateSiteUrlByAccountId(account.getId) +
        GenericLinkUtils.URL_PREFIX_PARAM + "account/billing"
      val cal: Calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
      cal.setTime(account.getCreatedtime)
      cal.add(Calendar.DATE, NUM_DAY_FREE_TRIAL)
      contentGenerator.putVariable("expireDay", df.format(cal.getTime))
      contentGenerator.putVariable("userName", user.getUsername)
      contentGenerator.putVariable("link", link)
      extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName,
        Arrays.asList(new MailRecipientField(user.getEmail, user.getDisplayName)), null, null,
        contentGenerator.parseString("Your trial is about to end"),
        contentGenerator.parseFile(INFORM_FILLING_BILLING_INFORMATION_TEMPLATE, Locale.US), null)
    }
  }
}
