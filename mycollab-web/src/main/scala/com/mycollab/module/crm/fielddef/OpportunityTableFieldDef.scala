package com.mycollab.module.crm.fielddef

import com.mycollab.common.TableViewField
import com.mycollab.common.i18n.GenericI18Enum
import com.mycollab.module.crm.i18n.OpportunityI18nEnum
import com.mycollab.vaadin.web.ui.WebUIConstants

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
object OpportunityTableFieldDef {
  val selected = new TableViewField(null, "selected", WebUIConstants.TABLE_CONTROL_WIDTH)
  val action = new TableViewField(null, "id", WebUIConstants.TABLE_ACTION_CONTROL_WIDTH)
  val opportunityName = new TableViewField(GenericI18Enum.FORM_NAME, "opportunityname", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val currency = new TableViewField(GenericI18Enum.FORM_CURRENCY, "currency", WebUIConstants.TABLE_S_LABEL_WIDTH)
  val amount = new TableViewField(OpportunityI18nEnum.FORM_AMOUNT, "amount", WebUIConstants.TABLE_S_LABEL_WIDTH)
  val probability = new TableViewField(OpportunityI18nEnum.FORM_PROBABILITY, "probability", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val accountName = new TableViewField(OpportunityI18nEnum.FORM_ACCOUNT_NAME, "accountName", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val expectedCloseDate = new TableViewField(OpportunityI18nEnum.FORM_EXPECTED_CLOSE_DATE, "expectedcloseddate", WebUIConstants.TABLE_DATE_TIME_WIDTH)
  val `type` = new TableViewField(GenericI18Enum.FORM_TYPE, "type", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val leadSource = new TableViewField(OpportunityI18nEnum.FORM_SOURCE, "source", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val campaignName = new TableViewField(OpportunityI18nEnum.FORM_CAMPAIGN_NAME, "campaignName", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val assignUser = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignUserFullName", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val saleStage = new TableViewField(OpportunityI18nEnum.FORM_SALE_STAGE, "salesstage", WebUIConstants.TABLE_X_LABEL_WIDTH)
}
