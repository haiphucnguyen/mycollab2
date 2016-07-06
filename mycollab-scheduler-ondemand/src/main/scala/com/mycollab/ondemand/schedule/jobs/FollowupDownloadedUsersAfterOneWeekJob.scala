package com.mycollab.ondemand.schedule.jobs

import java.util.Arrays

import com.mycollab.module.mail.service.IContentGenerator
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.module.mail.service.ExtMailService
import com.mycollab.ondemand.module.support.dao.CommunityLeadMapper
import com.mycollab.ondemand.module.support.domain.CommunityLeadExample
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.joda.time.LocalDate
import org.quartz.{JobExecutionContext, JobExecutionException}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.2.8
  */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class FollowupDownloadedUsersAfterOneWeekJob extends GenericQuartzJobBean {
  @Autowired var communityLeadMapper: CommunityLeadMapper = _
  @Autowired var contentGenerator: IContentGenerator = _
  @Autowired var extMailService: ExtMailService = _

  @throws(classOf[JobExecutionException])
  def executeJob(context: JobExecutionContext): Unit = {
    val sevenDaysAgo = new LocalDate().minusDays(5)
    val ex = new CommunityLeadExample()
    ex.createCriteria().andRegisterdateEqualTo(sevenDaysAgo.toDate).andValidEqualTo(true)
    import collection.JavaConverters._
    val leads = communityLeadMapper.selectByExample(ex).asScala.toList
    for (customerLead <- leads) {
      val leadName = customerLead.getFirstname + " " + customerLead.getLastname
      contentGenerator.putVariable("lead", leadName)
      extMailService.sendHTMLMail("john.adam@mycollab.com", "John Adams",
        Arrays.asList(new MailRecipientField(customerLead.getEmail, leadName)),
        null, null, "How are things going with MyCollab?",
        contentGenerator.parseFile("mailFollowupDownloadedUserAfter1Week.ftl"), null)
    }
  }
}
