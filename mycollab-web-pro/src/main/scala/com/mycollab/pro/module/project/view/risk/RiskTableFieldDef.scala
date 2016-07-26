package com.mycollab.pro.module.project.view.risk

import com.mycollab.common.TableViewField
import com.mycollab.common.i18n.GenericI18Enum
import com.mycollab.module.project.i18n.RiskI18nEnum
import com.mycollab.vaadin.web.ui.WebUIConstants

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
object RiskTableFieldDef {
  val selected = new TableViewField(null, "selected", WebUIConstants.TABLE_CONTROL_WIDTH);
  val action = new TableViewField(null, "id", -1)
  val name = new TableViewField(GenericI18Enum.FORM_NAME, "riskname", WebUIConstants.TABLE_EX_LABEL_WIDTH)
  val description = new TableViewField(GenericI18Enum.FORM_DESCRIPTION, "description", WebUIConstants.TABLE_EX_LABEL_WIDTH)
  val raisedBy = new TableViewField(RiskI18nEnum.FORM_RAISED_BY, "raisedByUserFullName", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val assignUser = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignedToUserFullName", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val duedate = new TableViewField(GenericI18Enum.FORM_DUE_DATE, "datedue", WebUIConstants.TABLE_DATE_WIDTH)
  val status = new TableViewField(GenericI18Enum.FORM_STATUS, "status", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val response = new TableViewField(RiskI18nEnum.FORM_RESPONSE, "response", WebUIConstants.TABLE_EX_LABEL_WIDTH)
  val consequence = new TableViewField(RiskI18nEnum.FORM_CONSEQUENCE, "consequence", WebUIConstants.TABLE_EX_LABEL_WIDTH)
  val probability = new TableViewField(RiskI18nEnum.FORM_PROBABILITY, "probalitity", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val rating = new TableViewField(RiskI18nEnum.FORM_RATING, "level", WebUIConstants.TABLE_M_LABEL_WIDTH)
}
