package com.mycollab.ondemand.schedule.jobs

import com.google.common.base.MoreObjects
import com.mycollab.common.GenericLinkUtils
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.IDeploymentMode
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.module.billing.AccountReminderStatusContants
import com.mycollab.module.billing.AccountStatusConstants
import com.mycollab.module.mail.service.ExtMailService
import com.mycollab.module.mail.service.IContentGenerator
import com.mycollab.module.user.domain.BillingAccount
import com.mycollab.module.user.domain.BillingAccountWithOwners
import com.mycollab.module.user.service.BillingAccountService
import com.mycollab.ondemand.module.billing.service.BillingService
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.joda.time.DateTime
import org.joda.time.Duration
import org.joda.time.format.DateTimeFormat
import org.quartz.DisallowConcurrentExecution
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
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
                                    private val deploymentMode: IDeploymentMode) : GenericQuartzJobBean() {
    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(BillingSendingNotificationJob::class.java)
        private val DATE_REMIND_FOR_ENDING_TRIAL_1ST = 26
        private val NUM_DAY_FREE_TRIAL = 30
    }

    @Throws(JobExecutionException::class)
    override fun executeJob(context: JobExecutionContext) {
        val now = DateTime()

        val trialAccountsWithOwners = billingService.trialAccountsWithOwners
        trialAccountsWithOwners.forEach {
            val accountTrialFrom = DateTime(MoreObjects.firstNonNull(it.trialfrom, it.createdtime))
            val accountTrialTo = DateTime(MoreObjects.firstNonNull(it.trialto, accountTrialFrom.plusDays(NUM_DAY_FREE_TRIAL)))
            val durationDays = Duration(now, accountTrialTo).standardDays

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
            extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail(), SiteConfiguration.getDefaultSiteName(),
                    listOf(MailRecipientField(it.email, it.displayName)),
                    "Your trial has expired", contentGenerator.parseFile("mailInformAccountIsExpiredNotification.ftl", Locale.US))
        }
    }

    private fun sendRemindEmailAskUpdateBillingAccount(account: BillingAccountWithOwners, afterDay: Int) {
        val df = DateTimeFormat.forPattern("MM/dd/yyyy")

        account.owners.forEach {
            LOG.info("Send mail after " + afterDay + " days for username ${it.username} , mail ${it.email}")
            contentGenerator.putVariable("account", account)
            val link = "${deploymentMode.getSiteUrl(account.subdomain)}${GenericLinkUtils.URL_PREFIX_PARAM}account/billing"
            val accountTrialFrom = DateTime(MoreObjects.firstNonNull(account.trialfrom, account.createdtime))
            val accountTrialTo = DateTime(MoreObjects.firstNonNull(account.trialto, accountTrialFrom.plusDays(NUM_DAY_FREE_TRIAL)))
            contentGenerator.putVariable("expireDay", df.print(accountTrialTo))
            contentGenerator.putVariable("userName", it.lastname)
            contentGenerator.putVariable("link", link)
            extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail(), SiteConfiguration.getDefaultSiteName(),
                    Collections.singletonList(MailRecipientField(it.email, it.displayName)),
                    "Your trial will end soon", contentGenerator.parseFile("mailRemindAccountIsAboutExpiredNotification.ftl", Locale.US))
        }
    }
}