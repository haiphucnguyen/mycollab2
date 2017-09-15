package com.mycollab.ondemand.schedule.jobs

import java.util.Arrays

import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.db.arguments.SearchCriteria.OrderField
import com.mycollab.db.arguments.{BasicSearchRequest, SearchCriteria}
import com.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.mycollab.module.user.domain.SimpleUser
import com.mycollab.module.user.domain.criteria.UserSearchCriteria
import com.mycollab.module.user.service.UserService
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.joda.time.LocalDateTime
import org.quartz.{JobExecutionContext, JobExecutionException}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
  *
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class SendingCountUserLoginByDateJob extends GenericQuartzJobBean {
  private val LOG: Logger = LoggerFactory.getLogger(classOf[SendingCountUserLoginByDateJob])
  private val COUNT_USER_LOGIN_TEMPLATE: String = "mailCountUserLoginByDate.ftl"

  @Autowired private val userService: UserService = null
  @Autowired private val extMailService: ExtMailService = null
  @Autowired private val contentGenerator: IContentGenerator = null

  @SuppressWarnings(Array("unchecked"))
  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext): Unit = {
    val criteria = new UserSearchCriteria
    val to = new LocalDateTime()
    val from = to.minusDays(1)
    criteria.setSaccountid(null)
    criteria.setLastAccessTimeRange(from.toDate, to.toDate)
    criteria.setOrderFields(Arrays.asList(new OrderField("subDomain", SearchCriteria.ASC)))

    import scala.collection.JavaConverters._
    val accessedUsers = userService.findPageableListByCriteria(new BasicSearchRequest[UserSearchCriteria](criteria)).asScala.toList.asInstanceOf[List[SimpleUser]]
    if (accessedUsers.nonEmpty) {
      contentGenerator.putVariable("lstUser", accessedUsers)
      contentGenerator.putVariable("count", accessedUsers.size)
      try {
        extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getNotifyEmail,
          Arrays.asList(new MailRecipientField("hainguyen@esofthead.com", "Hai Nguyen")),
          "Today system-logins count", contentGenerator.parseFile(COUNT_USER_LOGIN_TEMPLATE))
      }
      catch {
        case e: Exception => LOG.error("Error while generate template", e)
      }
    }
  }
}
