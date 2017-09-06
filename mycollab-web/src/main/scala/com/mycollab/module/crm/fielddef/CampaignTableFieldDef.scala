package com.mycollab.module.crm.fielddef

import com.mycollab.common.TableViewField
import com.mycollab.common.i18n.GenericI18Enum
import com.mycollab.module.crm.i18n.CampaignI18nEnum
import com.mycollab.vaadin.web.ui.WebUIConstants

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
object CampaignTableFieldDef {
  val selected = new TableViewField(null, "selected", WebUIConstants.TABLE_CONTROL_WIDTH)
  val action = new TableViewField(null, "id", WebUIConstants.TABLE_ACTION_CONTROL_WIDTH)
  val actualcost = new TableViewField(CampaignI18nEnum.FORM_ACTUAL_COST, "actualcost", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val budget = new TableViewField(CampaignI18nEnum.FORM_BUDGET, "budget", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val campaignname = new TableViewField(GenericI18Enum.FORM_NAME, "campaignname", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val status = new TableViewField(GenericI18Enum.FORM_STATUS, "status", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val `type` = new TableViewField(GenericI18Enum.FORM_TYPE, "type", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val expectedCost = new TableViewField(CampaignI18nEnum.FORM_EXPECTED_COST, "expectedcost", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val expectedRevenue = new TableViewField(CampaignI18nEnum.FORM_EXPECTED_REVENUE, "expectedrevenue", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val endDate = new TableViewField(GenericI18Enum.FORM_END_DATE, "enddate", WebUIConstants.TABLE_DATE_WIDTH)
  val startDate = new TableViewField(GenericI18Enum.FORM_START_DATE, "startdate", WebUIConstants.TABLE_DATE_WIDTH)
  val assignUser = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignUserFullName", WebUIConstants.TABLE_X_LABEL_WIDTH)
}
