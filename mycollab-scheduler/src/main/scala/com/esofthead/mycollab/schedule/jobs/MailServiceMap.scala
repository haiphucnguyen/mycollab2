package com.esofthead.mycollab.schedule.jobs

import com.esofthead.mycollab.core.MyCollabException
import com.esofthead.mycollab.module.crm.CrmTypeConstants
import com.esofthead.mycollab.module.project.ProjectTypeConstants
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction
import com.esofthead.mycollab.schedule.email.crm._
import com.esofthead.mycollab.schedule.email.project._

/**
  * @author MyCollab Ltd
  * @since 5.2.5
  */
object MailServiceMap {
  private val serviceMap2: Map[String, Class[_ <: SendingRelayEmailNotificationAction]] = Map(
    ProjectTypeConstants.BUG -> classOf[BugRelayEmailNotificationAction],
    ProjectTypeConstants.BUG_COMPONENT -> classOf[ComponentRelayEmailNotificationAction],
    ProjectTypeConstants.BUG_VERSION -> classOf[VersionRelayEmailNotificationAction],
    ProjectTypeConstants.MESSAGE -> classOf[MessageRelayEmailNotificationAction],
    ProjectTypeConstants.MILESTONE -> classOf[ProjectMilestoneRelayEmailNotificationAction],
    ProjectTypeConstants.PAGE -> classOf[ProjectPageRelayEmailNotificationAction],
    ProjectTypeConstants.PROBLEM -> classOf[ProjectProblemRelayEmailNotificationAction],
    ProjectTypeConstants.PROJECT -> classOf[ProjectRelayEmailNotificationAction],
    ProjectTypeConstants.RISK -> classOf[ProjectRiskRelayEmailNotificationAction],
    ProjectTypeConstants.STANDUP -> classOf[StandupRelayEmailNotificationAction],
    ProjectTypeConstants.TASK -> classOf[ProjectTaskRelayEmailNotificationAction],
    CrmTypeConstants.ACCOUNT -> classOf[AccountRelayEmailNotificationAction],
    CrmTypeConstants.CALL -> classOf[CallRelayEmailNotificationAction],
    CrmTypeConstants.CAMPAIGN -> classOf[CampaignRelayEmailNotificationAction],
    CrmTypeConstants.CASE -> classOf[CaseRelayEmailNotificationAction],
    CrmTypeConstants.CONTACT -> classOf[ContactRelayEmailNotificationAction],
    CrmTypeConstants.LEAD -> classOf[LeadRelayEmailNotificationAction],
    CrmTypeConstants.MEETING -> classOf[MeetingRelayEmailNotificationAction],
    CrmTypeConstants.OPPORTUNITY -> classOf[OpportunityRelayEmailNotificationAction],
    CrmTypeConstants.TASK -> classOf[TaskRelayEmailNotificationAction])

  def service(typeVal: String): Class[_ <: SendingRelayEmailNotificationAction] = {
    val actionCls = serviceMap2(typeVal)
    if (actionCls == null) {
      throw new MyCollabException("Can not find associate email action for " + typeVal)
    }
    return actionCls;
  }
}
