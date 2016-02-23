package com.esofthead.mycollab.module.crm.view.opportunity

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.module.crm.i18n.OpportunityI18nEnum
import com.esofthead.mycollab.vaadin.web.ui.UIConstants

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
object OpportunityTableFieldDef {
  val selected = new TableViewField(null, "selected", UIConstants.TABLE_CONTROL_WIDTH)
  val action = new TableViewField(null, "id", UIConstants.TABLE_ACTION_CONTROL_WIDTH)
  val opportunityName = new TableViewField(OpportunityI18nEnum.FORM_NAME, "opportunityname", UIConstants.TABLE_X_LABEL_WIDTH)
  val currency = new TableViewField(OpportunityI18nEnum.FORM_CURRENCY, "currency", UIConstants.TABLE_S_LABEL_WIDTH)
  val amount = new TableViewField(OpportunityI18nEnum.FORM_AMOUNT, "amount", UIConstants.TABLE_S_LABEL_WIDTH)
  val probability = new TableViewField(OpportunityI18nEnum.FORM_PROBABILITY, "probability", UIConstants.TABLE_X_LABEL_WIDTH)
  val accountName = new TableViewField(OpportunityI18nEnum.FORM_ACCOUNT_NAME, "accountName", UIConstants.TABLE_X_LABEL_WIDTH)
  val expectedCloseDate = new TableViewField(OpportunityI18nEnum.FORM_EXPECTED_CLOSE_DATE, "expectedcloseddate", UIConstants.TABLE_DATE_TIME_WIDTH)
  val `type` = new TableViewField(OpportunityI18nEnum.FORM_TYPE, "type", UIConstants.TABLE_X_LABEL_WIDTH)
  val leadSource = new TableViewField(OpportunityI18nEnum.FORM_SOURCE, "source", UIConstants.TABLE_X_LABEL_WIDTH)
  val campaignName = new TableViewField(OpportunityI18nEnum.FORM_CAMPAIGN_NAME, "campaignName", UIConstants.TABLE_X_LABEL_WIDTH)
  val assignUser = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH)
  val saleStage = new TableViewField(OpportunityI18nEnum.FORM_SALE_STAGE, "salesstage", UIConstants.TABLE_X_LABEL_WIDTH)
}
