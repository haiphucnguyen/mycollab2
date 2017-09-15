package com.mycollab.ondemand.schedule.jobs

import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.db.arguments.BasicSearchRequest
import com.mycollab.db.arguments.SearchCriteria
import com.mycollab.module.mail.service.ExtMailService
import com.mycollab.module.mail.service.IContentGenerator
import com.mycollab.module.user.domain.SimpleUser
import com.mycollab.module.user.domain.criteria.UserSearchCriteria
import com.mycollab.module.user.service.UserService
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.joda.time.LocalDateTime
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
class SendingCountUserLoginByDateJob(private val userService: UserService,
                                     private val extMailService: ExtMailService,
                                     private val contentGenerator: IContentGenerator) : GenericQuartzJobBean() {
    private val COUNT_USER_LOGIN_TEMPLATE = "mailCountUserLoginByDate.ftl"

    @Throws(JobExecutionException::class)
    override fun executeJob(context: JobExecutionContext) {
        val criteria = UserSearchCriteria()
        val to = LocalDateTime()
        val from = to.minusDays(1)
        criteria.saccountid = null
        criteria.setLastAccessTimeRange(from.toDate(), to.toDate())
        criteria.setOrderFields(mutableListOf(SearchCriteria.OrderField("subDomain", SearchCriteria.ASC)))

        val accessedUsers = userService.findPageableListByCriteria(BasicSearchRequest(criteria)) as List<SimpleUser>
        if (accessedUsers.isNotEmpty()) {
            contentGenerator.putVariable("lstUser", accessedUsers)
            contentGenerator.putVariable("count", accessedUsers.size)

            extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail(), SiteConfiguration.getNotifyEmail(),
                    listOf(MailRecipientField("hainguyen@esofthead.com", "Hai Nguyen")),
                    "Today system-logins count", contentGenerator.parseFile(COUNT_USER_LOGIN_TEMPLATE))

        }
    }
}