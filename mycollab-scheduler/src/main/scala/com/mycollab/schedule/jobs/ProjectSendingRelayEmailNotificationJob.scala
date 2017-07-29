package com.mycollab.schedule.jobs

import com.mycollab.module.project.service.ProjectService
import com.mycollab.common.MonitorTypeConstants
import com.mycollab.common.dao.RelayEmailNotificationMapper
import com.mycollab.spring.AppContextUtil
import org.quartz.{DisallowConcurrentExecution, JobExecutionContext}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.{Profile, Scope}
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
@Component
@Profile(Array("production"))
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@DisallowConcurrentExecution
class ProjectSendingRelayEmailNotificationJob extends GenericQuartzJobBean {
  private val LOG: Logger = LoggerFactory.getLogger(classOf[ProjectSendingRelayEmailNotificationJob])

  @Autowired
  private val projectService: ProjectService = null

  @Autowired
  private val relayNotificationMapper: RelayEmailNotificationMapper = null

  def executeJob(context: JobExecutionContext) {
    import scala.collection.JavaConverters._
    val relayEmailNotifications = projectService.findProjectRelayEmailNotifications.asScala.toList
    for (notification <- relayEmailNotifications) {
      try {
        val mailServiceCls = MailServiceMap.service(notification.getType)
        if (mailServiceCls != null) {
          val emailNotificationAction = AppContextUtil.getSpringBean(mailServiceCls)
          if (emailNotificationAction != null) {
            notification.getAction match {
              case MonitorTypeConstants.CREATE_ACTION => emailNotificationAction.sendNotificationForCreateAction(notification)
              case MonitorTypeConstants.UPDATE_ACTION => emailNotificationAction.sendNotificationForUpdateAction(notification)
              case MonitorTypeConstants.ADD_COMMENT_ACTION => emailNotificationAction.sendNotificationForCommentAction(notification)
            }
          } else {
            LOG.error("Can not find the spring class " + mailServiceCls)
          }
        }
      }
      catch {
        case ex: Exception => LOG.error("Error while sending scheduler command", ex)
      } finally {
        relayNotificationMapper.deleteByPrimaryKey(notification.getId)
      }
    }
  }
}
