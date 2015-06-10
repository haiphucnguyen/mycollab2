package com.esofthead.mycollab.module.crm.view.campaign

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.module.crm.i18n.CampaignI18nEnum
import com.esofthead.mycollab.vaadin.ui.UIConstants

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
object CampaignTableFieldDef {
    val selected: TableViewField = new TableViewField(null, "selected", UIConstants.TABLE_CONTROL_WIDTH)
    val action: TableViewField = new TableViewField(null, "id", UIConstants.TABLE_ACTION_CONTROL_WIDTH)
    val actualcost: TableViewField = new TableViewField(CampaignI18nEnum.FORM_ACTUAL_COST, "actualcost", UIConstants.TABLE_M_LABEL_WIDTH)
    val budget: TableViewField = new TableViewField(CampaignI18nEnum.FORM_BUDGET, "budget", UIConstants.TABLE_M_LABEL_WIDTH)
    val campaignname: TableViewField = new TableViewField(CampaignI18nEnum.FORM_CAMPAIGN_NAME, "campaignname", UIConstants.TABLE_X_LABEL_WIDTH)
    val status: TableViewField = new TableViewField(CampaignI18nEnum.FORM_STATUS, "status", UIConstants.TABLE_M_LABEL_WIDTH)
    val `type`: TableViewField = new TableViewField(CampaignI18nEnum.FORM_TYPE, "type", UIConstants.TABLE_S_LABEL_WIDTH)
    val expectedCost: TableViewField = new TableViewField(CampaignI18nEnum.FORM_EXPECTED_COST, "expectedcost", UIConstants.TABLE_M_LABEL_WIDTH)
    val expectedRevenue: TableViewField = new TableViewField(CampaignI18nEnum.FORM_EXPECTED_REVENUE, "expectedrevenue", UIConstants.TABLE_M_LABEL_WIDTH)
    val endDate: TableViewField = new TableViewField(CampaignI18nEnum.FORM_END_DATE, "enddate", UIConstants.TABLE_DATE_WIDTH)
    val startDate: TableViewField = new TableViewField(CampaignI18nEnum.FORM_START_DATE, "startdate", UIConstants.TABLE_DATE_WIDTH)
    val assignUser: TableViewField = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH)
}
