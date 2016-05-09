package com.esofthead.mycollab.module.project.view

import com.esofthead.mycollab.module.project.ProjectTypeConstants
import com.esofthead.mycollab.module.project.i18n._
import com.esofthead.mycollab.vaadin.AppContext

/**
  * @author MyCollab Ltd
  * @since 5.3.1
  */
object ProjectLocalizationTypeMap {
  def getType(key: String): String = {
    key match {
      case ProjectTypeConstants.PROJECT => AppContext.getMessage(ProjectI18nEnum.SINGLE)
      case ProjectTypeConstants.MESSAGE => AppContext.getMessage(MessageI18nEnum.SINGLE)
      case ProjectTypeConstants.MILESTONE => AppContext.getMessage(MilestoneI18nEnum.SINGLE)
      case ProjectTypeConstants.TASK => AppContext.getMessage(TaskI18nEnum.SINGLE)
      case ProjectTypeConstants.BUG => AppContext.getMessage(BugI18nEnum.SINGLE)
      case ProjectTypeConstants.BUG_COMPONENT => AppContext.getMessage(ComponentI18nEnum.SINGLE)
      case ProjectTypeConstants.BUG_VERSION => AppContext.getMessage(VersionI18nEnum.SINGLE)
      case ProjectTypeConstants.PAGE => AppContext.getMessage(PageI18nEnum.SINGLE)
      case ProjectTypeConstants.STANDUP => AppContext.getMessage(StandupI18nEnum.SINGLE)
      case ProjectTypeConstants.RISK => AppContext.getMessage(RiskI18nEnum.SINGLE)
      case _ => ""
    }
  }
}
