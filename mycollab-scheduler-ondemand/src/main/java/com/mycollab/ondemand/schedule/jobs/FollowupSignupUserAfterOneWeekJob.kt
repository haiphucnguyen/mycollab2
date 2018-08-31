package com.mycollab.ondemand.schedule.jobs

import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.IDeploymentMode
import com.mycollab.db.arguments.BasicSearchRequest
import com.mycollab.db.arguments.RangeDateSearchField
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.module.billing.AccountStatusConstants
import com.mycollab.module.mail.service.ExtMailService
import com.mycollab.module.mail.service.IContentGenerator
import com.mycollab.ondemand.module.billing.domain.criteria.BillingAccountSearchCriteria
import com.mycollab.ondemand.module.billing.service.BillingService
import com.mycollab.ondemand.module.support.SupportLinkGenerator
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.joda.time.DateTime
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class FollowupSignupUserAfterOneWeekJob(private val billingService: BillingService,
                                        private val contentGenerator: IContentGenerator,
                                        private val extMailService: ExtMailService,
                                        private val deploymentMode: IDeploymentMode) : GenericQuartzJobBean() {

    @Throws(JobExecutionException::class)
    override fun executeJob(context: JobExecutionContext) {
        val searchCriteria = BillingAccountSearchCriteria()
        val now = DateTime()
        searchCriteria.registerTimeDuration = RangeDateSearchField(now.minusDays(7).toDate(), now.minusDays(6).toDate())
        searchCriteria.statuses = SetSearchField(AccountStatusConstants.TRIAL)
        val accounts = billingService.findPageableListByCriteria(BasicSearchRequest(searchCriteria))
        accounts.forEach { account ->
            val accountOwners = account.accountOwners
            accountOwners?.forEach {
                if (it.canSendEmail!!) {
                    val leadName = "${it.firstname} ${it.lastname}"
                    contentGenerator.putVariable("lead", leadName)
                    contentGenerator.putVariable("unsubscribeUrl",
                            SupportLinkGenerator.generateUnsubscribeEmailFullLink(deploymentMode.getSiteUrl("settings"),
                                    it.email))
                    LOG.info("Send follow up email to sign up user ${it.email}")
                    extMailService.sendHTMLMail("john.adam@mycollab.com", "John Adams",
                            listOf(MailRecipientField(it.email, leadName)),
                            "How are things going with MyCollab?",
                            contentGenerator.parseFile("mailFollowupSignupUserAfter1Week.ftl"))
                }
            }
        }
    }

    companion object {
        val LOG = LoggerFactory.getLogger(FollowupSignupUserAfterOneWeekJob::class.java)
    }
}