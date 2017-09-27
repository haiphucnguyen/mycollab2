package com.mycollab.module.crm.fielddef

import com.mycollab.common.TableViewField
import com.mycollab.common.i18n.GenericI18Enum
import com.mycollab.module.crm.i18n.LeadI18nEnum
import com.mycollab.vaadin.web.ui.WebUIConstants

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object LeadTableFieldDef {
    @JvmField val selected = TableViewField(null, "selected", WebUIConstants.TABLE_CONTROL_WIDTH)
    @JvmField val action = TableViewField(null, "id", WebUIConstants.TABLE_ACTION_CONTROL_WIDTH)
    @JvmField val name = TableViewField(GenericI18Enum.FORM_NAME, "leadName", WebUIConstants.TABLE_X_LABEL_WIDTH)
    @JvmField val title = TableViewField(LeadI18nEnum.FORM_TITLE, "title", WebUIConstants.TABLE_S_LABEL_WIDTH)
    @JvmField val department = TableViewField(LeadI18nEnum.FORM_DEPARTMENT, "department", WebUIConstants.TABLE_X_LABEL_WIDTH)
    @JvmField val accountName = TableViewField(LeadI18nEnum.FORM_ACCOUNT_NAME, "accountname", WebUIConstants.TABLE_X_LABEL_WIDTH)
    @JvmField val leadSource = TableViewField(LeadI18nEnum.FORM_LEAD_SOURCE, "leadsourcedesc", WebUIConstants.TABLE_S_LABEL_WIDTH)
    @JvmField val industry = TableViewField(LeadI18nEnum.FORM_INDUSTRY, "industry", WebUIConstants.TABLE_M_LABEL_WIDTH)
    @JvmField val email = TableViewField(GenericI18Enum.FORM_EMAIL, "email", WebUIConstants.TABLE_EMAIL_WIDTH)
    @JvmField val phoneoffice = TableViewField(LeadI18nEnum.FORM_OFFICE_PHONE, "officephone", WebUIConstants.TABLE_M_LABEL_WIDTH)
    @JvmField val mobile = TableViewField(LeadI18nEnum.FORM_MOBILE, "mobile", WebUIConstants.TABLE_M_LABEL_WIDTH)
    @JvmField val fax = TableViewField(LeadI18nEnum.FORM_FAX, "fax", WebUIConstants.TABLE_M_LABEL_WIDTH)
    @JvmField val status = TableViewField(GenericI18Enum.FORM_STATUS, "status", WebUIConstants.TABLE_M_LABEL_WIDTH)
    @JvmField val website = TableViewField(LeadI18nEnum.FORM_WEBSITE, "website", WebUIConstants.TABLE_X_LABEL_WIDTH)
    @JvmField val assignedUser = TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignUserFullName", WebUIConstants.TABLE_X_LABEL_WIDTH)
}