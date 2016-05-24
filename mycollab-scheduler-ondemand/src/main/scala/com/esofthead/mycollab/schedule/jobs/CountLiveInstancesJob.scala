package com.esofthead.mycollab.schedule.jobs

import java.util.Arrays

import com.esofthead.mycollab.common.dao.LiveInstanceMapper
import com.esofthead.mycollab.common.domain.{LiveInstanceExample, MailRecipientField}
import com.esofthead.mycollab.configuration.SiteConfiguration
import com.esofthead.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import org.quartz.{JobExecutionContext, JobExecutionException}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.2.7
  */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class CountLiveInstancesJob extends GenericQuartzJobBean {
  @Autowired private val liveInstanceMapper: LiveInstanceMapper = null
  @Autowired var extMailService: ExtMailService = _
  @Autowired var contentGenerator: IContentGenerator = _

  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext): Unit = {
    val ex = new LiveInstanceExample
    ex.setOrderByClause("appVersion DESC")
    import scala.collection.JavaConverters._
    val liveInstances = liveInstanceMapper.selectByExample(ex).asScala.toList
    contentGenerator.putVariable("instances", liveInstances)
    contentGenerator.putVariable("count", liveInstances.size)
    extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getNotifyEmail,
      Arrays.asList(new MailRecipientField("hainguyen@esofthead.com", "Hai Nguyen")), null, null,
      contentGenerator.parseString("Today live instances count"),
      contentGenerator.parseFile("templates/email/user/countLiveInstances.mt"), null)
  }
}
