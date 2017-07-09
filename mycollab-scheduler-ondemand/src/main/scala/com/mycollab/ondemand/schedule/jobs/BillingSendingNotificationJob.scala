package com.mycollab.ondemand.schedule.jobs

import java.util.{Collections, Locale}

import com.google.common.base.MoreObjects
import com.mycollab.common.GenericLinkUtils
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.{ApplicationConfiguration, EmailConfiguration, IDeploymentMode}
import com.mycollab.module.billing.{AccountReminderStatusContants, AccountStatusConstants}
import com.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.mycollab.module.user.domain.{BillingAccount, BillingAccountWithOwners}
import com.mycollab.module.user.service.BillingAccountService
import com.mycollab.ondemand.module.billing.service.BillingService
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.joda.time.format.DateTimeFormat
import org.joda.time.{DateTime, Duration}
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
  private val DATE_REMIND_FOR_ENDING_TRIAL_1ST: Integer = 26
  private val NUM_DAY_FREE_TRIAL: Integer = 30

  @Autowired private val billingService: BillingService = null
  @Autowired private val billingAccountService: BillingAccountService = null
  @Autowired private val emailConfiguration: EmailConfiguration = null
  @Autowired private val extMailService: ExtMailService = null
  @Autowired private val contentGenerator: IContentGenerator = null
  @Autowired private val deploymentMode: IDeploymentMode = null
  @Autowired private val applicationConfiguration: ApplicationConfiguration = null

  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext) {
    val now = new DateTime()

    import scala.collection.JavaConverters._
    val trialAccountsWithOwners = billingService.getTrialAccountsWithOwners.asScala.toList

    for (account <- trialAccountsWithOwners) {
      val accountTrialFrom = new DateTime(MoreObjects.firstNonNull(account.getTrialfrom, account.getCreatedtime))
      val accountTrialTo = new DateTime(MoreObjects.firstNonNull(account.getTrialto, accountTrialFrom.plusDays(NUM_DAY_FREE_TRIAL)))
      val durationDays = new Duration(now, accountTrialTo).getStandardDays

      if (durationDays < (NUM_DAY_FREE_TRIAL - DATE_REMIND_FOR_ENDING_TRIAL_1ST) && (account.getReminderstatus == null)) {
        sendRemindEmailAskUpdateBillingAccount(account, DATE_REMIND_FOR_ENDING_TRIAL_1ST)
        val billingAccount = new BillingAccount
        billingAccount.setId(account.getId)
        billingAccount.setReminderstatus(AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_1ST_TIME)
        billingAccountService.updateSelectiveWithSession(billingAccount, "")
      } else if (durationDays < -1) {
        LOG.debug("Check whether account exceed 32 days to convert to basic plan")
        val billingAccount = new BillingAccount
        billingAccount.setId(account.getId)
        billingAccount.setStatus(AccountStatusConstants.INVALID)
        billingAccountService.updateSelectiveWithSession(billingAccount, "")
        sendingEmailInformAccountIsInvalid(account)
      }
    }
  }

  private def sendingEmailInformAccountIsInvalid(account: BillingAccountWithOwners) {
    import scala.collection.JavaConverters._
    for (user <- account.getOwners.asScala) {
      LOG.info("Send mail after 32 days for username {} , mail {}", Array(user.getUsername, user.getEmail))
      contentGenerator.putVariable("account", account)
      contentGenerator.putVariable("userName", user.getLastname)
      val link = deploymentMode.getSiteUrl(account.getSubdomain) + GenericLinkUtils.URL_PREFIX_PARAM + "account/billing"
      contentGenerator.putVariable("link", link)
      extMailService.sendHTMLMail(emailConfiguration.getNotifyEmail, applicationConfiguration.getName,
        Collections.singletonList(new MailRecipientField(user.getEmail, user.getDisplayName)),
        "Your trial has expired", contentGenerator.parseFile("mailInformAccountIsExpiredNotification.ftl", Locale.US))
    }
  }

  private def sendRemindEmailAskUpdateBillingAccount(account: BillingAccountWithOwners, afterDay: Integer) {
    val df = DateTimeFormat.forPattern("MM/dd/yyyy")
    import scala.collection.JavaConverters._
    for (user <- account.getOwners.asScala) {
      LOG.info("Send mail after " + afterDay + " days for username {} , mail {}", Array(user.getUsername, user.getEmail))
      contentGenerator.putVariable("account", account)
      val link = deploymentMode.getSiteUrl(account.getSubdomain) + GenericLinkUtils.URL_PREFIX_PARAM + "account/billing"
      val accountTrialFrom = new DateTime(MoreObjects.firstNonNull(account.getTrialfrom, account.getCreatedtime))
      val accountTrialTo = new DateTime(MoreObjects.firstNonNull(account.getTrialto, accountTrialFrom.plusDays(NUM_DAY_FREE_TRIAL)))
      contentGenerator.putVariable("expireDay", df.print(accountTrialTo))
      contentGenerator.putVariable("userName", user.getLastname)
      contentGenerator.putVariable("link", link)
      extMailService.sendHTMLMail(emailConfiguration.getNotifyEmail, applicationConfiguration.getName,
        Collections.singletonList(new MailRecipientField(user.getEmail, user.getDisplayName)),
        "Your trial will end soon", contentGenerator.parseFile("mailRemindAccountIsAboutExpiredNotification.ftl", Locale.US))
    }
  }
}
