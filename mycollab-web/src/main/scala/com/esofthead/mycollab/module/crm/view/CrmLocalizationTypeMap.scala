package com.esofthead.mycollab.module.crm.view

import com.esofthead.mycollab.module.crm.CrmTypeConstants
import com.esofthead.mycollab.module.crm.i18n._
import com.esofthead.mycollab.vaadin.AppContext

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
object CrmLocalizationTypeMap {

  def getType(key: String): String = {
    key match {
      case CrmTypeConstants.ACCOUNT => AppContext.getMessage(AccountI18nEnum.SINGLE)
      case CrmTypeConstants.CALL => AppContext.getMessage(CallI18nEnum.SINGLE)
      case CrmTypeConstants.CAMPAIGN => AppContext.getMessage(CampaignI18nEnum.SINGLE)
      case CrmTypeConstants.CASE => AppContext.getMessage(CaseI18nEnum.SINGLE)
      case CrmTypeConstants.CONTACT => AppContext.getMessage(ContactI18nEnum.SINGLE)
      case CrmTypeConstants.LEAD => AppContext.getMessage(LeadI18nEnum.SINGLE)
      case CrmTypeConstants.MEETING => AppContext.getMessage(MeetingI18nEnum.SINGLE)
      case CrmTypeConstants.OPPORTUNITY => AppContext.getMessage(OpportunityI18nEnum.SINGLE)
      case CrmTypeConstants.TASK => AppContext.getMessage(TaskI18nEnum.SINGLE)
      case _ => ""
    }
  }
}
