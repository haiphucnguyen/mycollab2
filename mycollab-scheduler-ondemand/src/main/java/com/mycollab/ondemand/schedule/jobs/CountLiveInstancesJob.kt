package com.mycollab.ondemand.schedule.jobs

import com.mycollab.common.domain.LiveInstanceExample
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.module.mail.service.ExtMailService
import com.mycollab.module.mail.service.IContentGenerator
import com.mycollab.pro.common.dao.LiveInstanceMapper
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class CountLiveInstancesJob(private val liveInstanceMapper: LiveInstanceMapper,
                            private val contentGenerator: IContentGenerator,
                            private val extMailService: ExtMailService) : GenericQuartzJobBean() {

    @Throws(JobExecutionException::class)
    override fun executeJob(context: JobExecutionContext) {
        val ex = LiveInstanceExample()
        ex.orderByClause = "appVersion DESC"
        val liveInstances = liveInstanceMapper.selectByExample(ex)
        contentGenerator.putVariable("instances", liveInstances)
        contentGenerator.putVariable("count", liveInstances.size)
        extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail(), SiteConfiguration.getNotifyEmail(),
                listOf(MailRecipientField("haiphucnguyen@gmail.com", "Hai Nguyen")),
                "Today live instances count", contentGenerator.parseFile("mailCountLiveInstances.ftl"))
    }
}