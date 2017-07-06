package com.mycollab.ondemand.schedule.jobs

import java.util.Arrays

import com.mycollab.common.domain.{LiveInstanceExample, MailRecipientField}
import com.mycollab.configuration.EmailConfiguration
import com.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.mycollab.pro.common.dao.LiveInstanceMapper
import com.mycollab.schedule.jobs.GenericQuartzJobBean
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
  @Autowired private val extMailService: ExtMailService = null
  @Autowired private val emailConfiguration: EmailConfiguration = null
  @Autowired private val contentGenerator: IContentGenerator = null

  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext): Unit = {
    val ex = new LiveInstanceExample
    ex.setOrderByClause("appVersion DESC")
    import scala.collection.JavaConverters._
    val liveInstances = liveInstanceMapper.selectByExample(ex).asScala.toList
    contentGenerator.putVariable("instances", liveInstances)
    contentGenerator.putVariable("count", liveInstances.size)
    extMailService.sendHTMLMail(emailConfiguration.getNotifyEmail, emailConfiguration.getNotifyEmail,
      Arrays.asList(new MailRecipientField("hainguyen@esofthead.com", "Hai Nguyen")),
      "Today live instances count", contentGenerator.parseFile("mailCountLiveInstances.ftl"))
  }
}
