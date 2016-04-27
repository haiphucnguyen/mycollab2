package com.esofthead.mycollab.schedule.jobs

import java.util.Arrays

import com.esofthead.mycollab.common.domain.MailRecipientField
import com.esofthead.mycollab.configuration.SiteConfiguration
import com.esofthead.mycollab.core.arguments.BasicSearchRequest
import com.esofthead.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.esofthead.mycollab.module.user.domain.SimpleUser
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria
import com.esofthead.mycollab.module.user.service.UserService
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
  private val COUNT_USER_LOGIN_TEMPLATE: String = "templates/email/user/countUserLoginByDate.mt"

  @Autowired var userService: UserService = _
  @Autowired var extMailService: ExtMailService = _
  @Autowired var contentGenerator: IContentGenerator = _

  @SuppressWarnings(Array("unchecked"))
  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext): Unit = {
    val criteria = new UserSearchCriteria
    val to = new LocalDateTime()
    val from = to.minusDays(1)
    criteria.setSaccountid(null)
    criteria.setLastAccessTimeRange(from.toDate, to.toDate)

    import scala.collection.JavaConverters._
    val accessedUsers = userService.findPagableListByCriteria(new BasicSearchRequest[UserSearchCriteria](criteria, 0, Integer.MAX_VALUE)).asScala.toList.asInstanceOf[List[SimpleUser]]
    if (accessedUsers != null && accessedUsers.nonEmpty) {
      contentGenerator.putVariable("lstUser", accessedUsers)
      contentGenerator.putVariable("count", accessedUsers.size)
      try {
        extMailService.sendHTMLMail(SiteConfiguration.getNoReplyEmail, SiteConfiguration.getNoReplyEmail,
          Arrays.asList(new MailRecipientField("hainguyen@esofthead.com", "Hai Nguyen")), null, null,
          contentGenerator.parseString("Today system-logins count"),
          contentGenerator.parseFile(COUNT_USER_LOGIN_TEMPLATE), null)
      }
      catch {
        case e: Exception => LOG.error("Error while generate template", e)
      }
    }
  }
}
