package com.mycollab.ondemand.schedule.jobs

import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.IDeploymentMode
import com.mycollab.module.mail.service.ExtMailService
import com.mycollab.module.mail.service.IContentGenerator
import com.mycollab.ondemand.module.support.SupportLinkGenerator
import com.mycollab.ondemand.module.support.dao.CommunityLeadMapper
import com.mycollab.ondemand.module.support.domain.CommunityLeadExample
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.joda.time.LocalDate
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class FollowupDownloadedUsersAfterOneWeekJob(private val communityLeadMapper: CommunityLeadMapper,
                                             private val contentGenerator: IContentGenerator,
                                             private val extMailService: ExtMailService,
                                             private val deploymentMode: IDeploymentMode) : GenericQuartzJobBean() {

    @Throws(JobExecutionException::class)
    override fun executeJob(context: JobExecutionContext) {
        val sevenDaysAgo = LocalDate().minusDays(5)
        val ex = CommunityLeadExample()
        ex.createCriteria().andRegisterdateEqualTo(sevenDaysAgo.toDate()).andValidEqualTo(true)
        val leads = communityLeadMapper.selectByExample(ex)
        leads.forEach {
            val leadName = it.firstname + " " + it.lastname
            contentGenerator.putVariable("lead", leadName)
            contentGenerator.putVariable("unsubscribeUrl", SupportLinkGenerator.generateUnsubscribeEmailFullLink(deploymentMode.getSiteUrl("settings"), it.email))
            extMailService.sendHTMLMail("john.adam@mycollab.com", "John Adams",
                    listOf(MailRecipientField(it.email, leadName)),
                    "How are things going with MyCollab?", contentGenerator.parseFile("mailFollowupDownloadedUserAfter1Week.ftl"))
        }
    }
}