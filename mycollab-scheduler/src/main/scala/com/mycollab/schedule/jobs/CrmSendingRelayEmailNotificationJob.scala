package com.mycollab.schedule.jobs

import com.mycollab.common.MonitorTypeConstants
import com.mycollab.common.dao.RelayEmailNotificationMapper
import com.mycollab.common.domain.SimpleRelayEmailNotification
import com.mycollab.common.domain.criteria.RelayEmailNotificationSearchCriteria
import com.mycollab.common.service.RelayEmailNotificationService
import com.mycollab.db.arguments.{BasicSearchRequest, SetSearchField}
import com.mycollab.module.crm.CrmTypeConstants
import com.mycollab.schedule.email.SendingRelayEmailNotificationAction
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
class CrmSendingRelayEmailNotificationJob extends GenericQuartzJobBean {
  private val LOG: Logger = LoggerFactory.getLogger(classOf[CrmSendingRelayEmailNotificationJob])
  
  @Autowired private val relayEmailNotificationMapper: RelayEmailNotificationMapper = null
  
  @SuppressWarnings(Array("unchecked"))
  def executeJob(context: JobExecutionContext) {
    val relayEmailService = AppContextUtil.getSpringBean(classOf[RelayEmailNotificationService])
    val criteria = new RelayEmailNotificationSearchCriteria
    criteria.setTypes(new SetSearchField[String](CrmTypeConstants.ACCOUNT, CrmTypeConstants.CONTACT,
      CrmTypeConstants.CAMPAIGN, CrmTypeConstants.LEAD, CrmTypeConstants.OPPORTUNITY, CrmTypeConstants.CASE,
      CrmTypeConstants.TASK, CrmTypeConstants.MEETING, CrmTypeConstants.CALL))
    
    import scala.collection.JavaConverters._
    val relayEmaiNotifications = relayEmailService.findPageableListByCriteria(
      new BasicSearchRequest[RelayEmailNotificationSearchCriteria](criteria, 0,
        Integer.MAX_VALUE)).asScala.toList.asInstanceOf[List[SimpleRelayEmailNotification]]
    var emailNotificationAction: SendingRelayEmailNotificationAction = null
    
    for (notification <- relayEmaiNotifications) {
      try {
        val mailServiceCls = MailServiceMap.service(notification.getType)
        if (mailServiceCls != null) {
          emailNotificationAction = AppContextUtil.getSpringBean(mailServiceCls)
          
          if (emailNotificationAction != null) {
            notification.getAction match {
              case MonitorTypeConstants.CREATE_ACTION => emailNotificationAction.sendNotificationForCreateAction(notification)
              case MonitorTypeConstants.UPDATE_ACTION => emailNotificationAction.sendNotificationForUpdateAction(notification)
              case MonitorTypeConstants.ADD_COMMENT_ACTION => emailNotificationAction.sendNotificationForCommentAction(notification)
            }
          }
        }
      }
      catch {
        case ex: Exception => LOG.error("Error while send the schedule command " + notification.getType, ex)
      } finally {
        relayEmailNotificationMapper.deleteByPrimaryKey(notification.getId)
      }
    }
  }
}