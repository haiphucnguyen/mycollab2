package com.mycollab.ondemand.schedule.jobs

import com.google.common.base.MoreObjects
import com.mycollab.common.GenericLinkUtils
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.ApplicationConfiguration
import com.mycollab.configuration.IDeploymentMode
import com.mycollab.module.billing.AccountReminderStatusContants
import com.mycollab.module.billing.AccountStatusConstants
import com.mycollab.module.mail.service.ExtMailService
import com.mycollab.module.mail.service.IContentGenerator
import com.mycollab.module.user.domain.BillingAccount
import com.mycollab.module.user.domain.BillingAccountWithOwners
import com.mycollab.module.user.service.BillingAccountService
import com.mycollab.ondemand.module.billing.service.BillingService
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.quartz.DisallowConcurrentExecution
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@DisallowConcurrentExecution
class BillingSendingNotificationJob(private val billingService: BillingService,
                                    private val billingAccountService: BillingAccountService,
                                    private val extMailService: ExtMailService,
                                    private val contentGenerator: IContentGenerator,
                                    private val deploymentMode: IDeploymentMode,
                                    private val applicationConfiguration: ApplicationConfiguration) : GenericQuartzJobBean() {
    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(BillingSendingNotificationJob::class.java)
        private const val DATE_REMIND_FOR_ENDING_TRIAL_1ST = 26
        private const val NUM_DAY_FREE_TRIAL = 30L
    }

    @Throws(JobExecutionException::class)
    override fun executeJob(context: JobExecutionContext) {
        val now = LocalDate.now()

        val trialAccountsWithOwners = billingService.trialAccountsWithOwners
        trialAccountsWithOwners.forEach {
            LOG.info("Send the trial account will come to end soon to ${it.accountname}")
            val accountTrialFrom = MoreObjects.firstNonNull(it.trialfrom, it.createdtime.toLocalDate())
            val accountTrialTo = MoreObjects.firstNonNull(it.trialto, accountTrialFrom.plusDays(NUM_DAY_FREE_TRIAL))
            val durationDays = Period.between(now, accountTrialTo).days

            if (durationDays < (NUM_DAY_FREE_TRIAL - DATE_REMIND_FOR_ENDING_TRIAL_1ST) && (it.reminderstatus == null)) {
                sendRemindEmailAskUpdateBillingAccount(it, DATE_REMIND_FOR_ENDING_TRIAL_1ST)
                val billingAccount = BillingAccount()
                billingAccount.id = it.id
                billingAccount.reminderstatus = AccountReminderStatusContants.REMIND_ACCOUNT_IS_ABOUT_END_1ST_TIME
                billingAccountService.updateSelectiveWithSession(billingAccount, "")
            } else if (durationDays < -1) {
                LOG.debug("Check whether account exceed 32 days to convert to basic plan")
                val billingAccount = BillingAccount()
                billingAccount.id = it.id
                billingAccount.status = AccountStatusConstants.INVALID
                billingAccountService.updateSelectiveWithSession(billingAccount, "")
                sendingEmailInformAccountIsInvalid(it)
            }
        }
    }

    private fun sendingEmailInformAccountIsInvalid(account: BillingAccountWithOwners) {
        account.owners.forEach {
            LOG.info("Send mail after 32 days for username ${it.username} , mail ${it.email}")
            contentGenerator.putVariable("account", account)
            contentGenerator.putVariable("userName", it.lastname)
            val link = "${deploymentMode.getSiteUrl(account.subdomain)}${GenericLinkUtils.URL_PREFIX_PARAM}account/billing"
            contentGenerator.putVariable("link", link)
            extMailService.sendHTMLMail(applicationConfiguration.notifyEmail, applicationConfiguration.siteName,
                    listOf(MailRecipientField(it.email, it.displayName)),
                    "Your trial has expired", contentGenerator.parseFile("mailInformAccountIsExpiredNotification.ftl", Locale.US))
        }
    }

    private fun sendRemindEmailAskUpdateBillingAccount(account: BillingAccountWithOwners, afterDay: Int) {
        val df = DateTimeFormatter.ofPattern("MM/dd/yyyy")

        account.owners.forEach {
            LOG.info("Send mail after $afterDay days for username ${it.username} , mail ${it.email}")
            contentGenerator.putVariable("account", account)
            val link = "${deploymentMode.getSiteUrl(account.subdomain)}${GenericLinkUtils.URL_PREFIX_PARAM}account/billing"
            val accountTrialFrom = MoreObjects.firstNonNull(account.trialfrom, account.createdtime.toLocalDate())
            val accountTrialTo = MoreObjects.firstNonNull(account.trialto, accountTrialFrom.plusDays(NUM_DAY_FREE_TRIAL))
            contentGenerator.putVariable("expireDay", df.format(accountTrialTo))
            contentGenerator.putVariable("userName", it.lastname)
            contentGenerator.putVariable("link", link)
            extMailService.sendHTMLMail(applicationConfiguration.notifyEmail, applicationConfiguration.siteName,
                    Collections.singletonList(MailRecipientField(it.email, it.displayName)),
                    "Your trial will end soon", contentGenerator.parseFile("mailRemindAccountIsAboutExpiredNotification.ftl", Locale.US))
        }
    }
}