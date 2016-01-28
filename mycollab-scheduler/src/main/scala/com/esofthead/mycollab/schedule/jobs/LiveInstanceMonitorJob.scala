package com.esofthead.mycollab.schedule.jobs

import com.esofthead.mycollab.common.domain.LiveInstance
import com.esofthead.mycollab.core.MyCollabVersion
import com.esofthead.mycollab.core.utils.MiscUtils
import org.joda.time.DateTime
import org.quartz.JobExecutionContext
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

/**
  * @author MyCollab Ltd
  * @since 5.2.6
  */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
class LiveInstanceMonitorJob extends GenericQuartzJobBean {
  def executeJob(context: JobExecutionContext): Unit = {
    val liveInstance = new LiveInstance()
    liveInstance.setAppversion(MyCollabVersion.getVersion())
    liveInstance.setInstalleddate(new DateTime().toDate())
    liveInstance.setJavaversion(System.getProperty("java.version"))
    liveInstance.setSysid(MiscUtils.getMacAddressOfServer())
    liveInstance.setSysproperties(System.getProperty("os.arch") + ":" + System.getProperty("os.name") + ":" +
      System.getProperty("os.name"))
    val restTemplate = new RestTemplate()
    restTemplate.postForObject("https://api.mycollab.com/api/checkInstance", liveInstance, classOf[String])
  }
}
