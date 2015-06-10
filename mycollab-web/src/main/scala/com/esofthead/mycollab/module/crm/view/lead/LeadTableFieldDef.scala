package com.esofthead.mycollab.module.crm.view.lead

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.module.crm.i18n.LeadI18nEnum
import com.esofthead.mycollab.vaadin.ui.UIConstants

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
object LeadTableFieldDef {
    val selected: TableViewField = new TableViewField(null, "selected", UIConstants.TABLE_CONTROL_WIDTH)
    val action: TableViewField = new TableViewField(null, "id", UIConstants.TABLE_ACTION_CONTROL_WIDTH)
    val name: TableViewField = new TableViewField(LeadI18nEnum.FORM_NAME, "leadName", UIConstants.TABLE_X_LABEL_WIDTH)
    val title: TableViewField = new TableViewField(LeadI18nEnum.FORM_TITLE, "title", UIConstants.TABLE_S_LABEL_WIDTH)
    val department: TableViewField = new TableViewField(LeadI18nEnum.FORM_DEPARTMENT, "department", UIConstants.TABLE_X_LABEL_WIDTH)
    val accountName: TableViewField = new TableViewField(LeadI18nEnum.FORM_ACCOUNT_NAME, "accountname", UIConstants.TABLE_X_LABEL_WIDTH)
    val leadSource: TableViewField = new TableViewField(LeadI18nEnum.FORM_LEAD_SOURCE, "leadsourcedesc", UIConstants.TABLE_S_LABEL_WIDTH)
    val industry: TableViewField = new TableViewField(LeadI18nEnum.FORM_INDUSTRY, "industry", UIConstants.TABLE_M_LABEL_WIDTH)
    val email: TableViewField = new TableViewField(LeadI18nEnum.FORM_EMAIL, "email", UIConstants.TABLE_EMAIL_WIDTH)
    val phoneoffice: TableViewField = new TableViewField(LeadI18nEnum.FORM_OFFICE_PHONE, "officephone", UIConstants.TABLE_M_LABEL_WIDTH)
    val mobile: TableViewField = new TableViewField(LeadI18nEnum.FORM_MOBILE, "mobile", UIConstants.TABLE_M_LABEL_WIDTH)
    val fax: TableViewField = new TableViewField(LeadI18nEnum.FORM_FAX, "fax", UIConstants.TABLE_M_LABEL_WIDTH)
    val status: TableViewField = new TableViewField(LeadI18nEnum.FORM_STATUS, "status", UIConstants.TABLE_M_LABEL_WIDTH)
    val website: TableViewField = new TableViewField(LeadI18nEnum.FORM_WEBSITE, "website", UIConstants.TABLE_X_LABEL_WIDTH)
    val assignedUser: TableViewField = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH)
}
