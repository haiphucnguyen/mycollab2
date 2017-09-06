package com.mycollab.module.project.view

import com.mycollab.module.project.ProjectTypeConstants
import com.mycollab.module.project.i18n._
import com.mycollab.vaadin.UserUIContext

/**
  * @author MyCollab Ltd
  * @since 5.3.1
  */
object ProjectLocalizationTypeMap {
  def getType(key: String): String = {
    key match {
      case ProjectTypeConstants.PROJECT => UserUIContext.getMessage(ProjectI18nEnum.SINGLE)
      case ProjectTypeConstants.MESSAGE => UserUIContext.getMessage(MessageI18nEnum.SINGLE)
      case ProjectTypeConstants.MILESTONE => UserUIContext.getMessage(MilestoneI18nEnum.SINGLE)
      case ProjectTypeConstants.TASK => UserUIContext.getMessage(TaskI18nEnum.SINGLE)
      case ProjectTypeConstants.BUG => UserUIContext.getMessage(BugI18nEnum.SINGLE)
      case ProjectTypeConstants.BUG_COMPONENT => UserUIContext.getMessage(ComponentI18nEnum.SINGLE)
      case ProjectTypeConstants.BUG_VERSION => UserUIContext.getMessage(VersionI18nEnum.SINGLE)
      case ProjectTypeConstants.PAGE => UserUIContext.getMessage(PageI18nEnum.SINGLE)
      case ProjectTypeConstants.STANDUP => UserUIContext.getMessage(StandupI18nEnum.SINGLE)
      case ProjectTypeConstants.RISK => UserUIContext.getMessage(RiskI18nEnum.SINGLE)
      case _ => ""
    }
  }
}
