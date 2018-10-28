package com.mycollab.pro.module.project.view.risk

import com.mycollab.common.GridFieldMeta
import com.mycollab.common.i18n.GenericI18Enum
import com.mycollab.module.project.i18n.RiskI18nEnum
import com.mycollab.vaadin.web.ui.WebUIConstants

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object RiskTableFieldDef {
    @JvmField val selected = GridFieldMeta(null, "selected", WebUIConstants.TABLE_CONTROL_WIDTH);
    @JvmField val action = GridFieldMeta(null, "id", -1)
    @JvmField val name = GridFieldMeta(GenericI18Enum.FORM_NAME, "riskname", WebUIConstants.TABLE_EX_LABEL_WIDTH)
    @JvmField val description = GridFieldMeta(GenericI18Enum.FORM_DESCRIPTION, "description", WebUIConstants.TABLE_EX_LABEL_WIDTH)
    @JvmField val raisedBy = GridFieldMeta(RiskI18nEnum.FORM_RAISED_BY, "raisedByUserFullName", WebUIConstants.TABLE_X_LABEL_WIDTH)
    @JvmField val assignUser = GridFieldMeta(GenericI18Enum.FORM_ASSIGNEE, "assignedToUserFullName", WebUIConstants.TABLE_X_LABEL_WIDTH)
    @JvmField val duedate = GridFieldMeta(GenericI18Enum.FORM_DUE_DATE, "datedue", WebUIConstants.TABLE_DATE_WIDTH)
    @JvmField val status = GridFieldMeta(GenericI18Enum.FORM_STATUS, "status", WebUIConstants.TABLE_M_LABEL_WIDTH)
    @JvmField val response = GridFieldMeta(RiskI18nEnum.FORM_RESPONSE, "response", WebUIConstants.TABLE_EX_LABEL_WIDTH)
    @JvmField val consequence = GridFieldMeta(RiskI18nEnum.FORM_CONSEQUENCE, "consequence", WebUIConstants.TABLE_EX_LABEL_WIDTH)
    @JvmField val probability = GridFieldMeta(RiskI18nEnum.FORM_PROBABILITY, "probalitity", WebUIConstants.TABLE_X_LABEL_WIDTH)
    @JvmField val priority = GridFieldMeta(GenericI18Enum.FORM_PRIORITY, "priority", WebUIConstants.TABLE_M_LABEL_WIDTH)
}