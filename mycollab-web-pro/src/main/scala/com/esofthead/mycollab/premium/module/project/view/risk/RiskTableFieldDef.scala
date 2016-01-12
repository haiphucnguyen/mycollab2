package com.esofthead.mycollab.premium.module.project.view.risk

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum
import com.esofthead.mycollab.vaadin.web.ui.UIConstants

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
object RiskTableFieldDef {
    val selected: TableViewField = new TableViewField(null, "selected", UIConstants.TABLE_CONTROL_WIDTH);
    val action: TableViewField = new TableViewField(null, "id", -1)
    val name: TableViewField = new TableViewField(RiskI18nEnum.FORM_NAME, "riskname", UIConstants.TABLE_EX_LABEL_WIDTH)
    val description: TableViewField = new TableViewField(RiskI18nEnum.FORM_DESCRIPTION, "description", UIConstants.TABLE_EX_LABEL_WIDTH)
    val raisedBy: TableViewField = new TableViewField(RiskI18nEnum.FORM_RAISED_BY, "raisedByUserFullName", UIConstants.TABLE_X_LABEL_WIDTH)
    val assignUser: TableViewField = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignedToUserFullName", UIConstants.TABLE_X_LABEL_WIDTH)
    val datedue: TableViewField = new TableViewField(RiskI18nEnum.FORM_DATE_DUE, "datedue", UIConstants.TABLE_DATE_WIDTH)
    val status: TableViewField = new TableViewField(RiskI18nEnum.FORM_STATUS, "status", UIConstants.TABLE_M_LABEL_WIDTH)
    val response: TableViewField = new TableViewField(RiskI18nEnum.FORM_RESPONSE, "response", UIConstants.TABLE_EX_LABEL_WIDTH)
    val consequence: TableViewField = new TableViewField(RiskI18nEnum.FORM_CONSEQUENCE, "consequence", UIConstants.TABLE_EX_LABEL_WIDTH)
    val probability: TableViewField = new TableViewField(RiskI18nEnum.FORM_PROBABILITY, "probalitity", UIConstants.TABLE_X_LABEL_WIDTH)
    val rating: TableViewField = new TableViewField(RiskI18nEnum.FORM_RATING, "level", UIConstants.TABLE_M_LABEL_WIDTH)
}
