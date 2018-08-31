package com.mycollab.ondemand.schedule.jobs

import com.mycollab.common.domain.LiveInstanceExample
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.ApplicationConfiguration
import com.mycollab.module.mail.service.ExtMailService
import com.mycollab.module.mail.service.IContentGenerator
import com.mycollab.pro.common.dao.LiveInstanceMapper
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class CountLiveInstancesJob(private val liveInstanceMapper: LiveInstanceMapper,
                            private val contentGenerator: IContentGenerator,
                            private val extMailService: ExtMailService,
                            private val applicationConfiguration: ApplicationConfiguration) : GenericQuartzJobBean() {

    @Throws(JobExecutionException::class)
    override fun executeJob(context: JobExecutionContext) {
        val ex = LiveInstanceExample()
        ex.orderByClause = "appVersion DESC"
        val liveInstances = liveInstanceMapper.selectByExample(ex)
        contentGenerator.putVariable("instances", liveInstances)
        contentGenerator.putVariable("count", liveInstances.size)
        LOG.info("Send detail ${liveInstances.size} instances to site admin")
        extMailService.sendHTMLMail(applicationConfiguration.notifyEmail, applicationConfiguration.notifyEmail,
                listOf(MailRecipientField("haiphucnguyen@gmail.com", "Hai Nguyen")),
                "Today live instances count", contentGenerator.parseFile("mailCountLiveInstances.ftl"))
    }

    companion object {
        val LOG = LoggerFactory.getLogger(CountLiveInstancesJob::class.java)
    }
}