package com.esofthead.mycollab.module.project.view.bug

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum
import com.esofthead.mycollab.vaadin.ui.UIConstants

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
object BugTableFieldDef {
    val action: TableViewField = new TableViewField(null, "id", UIConstants.TABLE_CONTROL_WIDTH)
    val summary: TableViewField = new TableViewField(BugI18nEnum.FORM_SUMMARY, "summary", UIConstants.TABLE_EX_LABEL_WIDTH)
    val description: TableViewField = new TableViewField(GenericI18Enum.FORM_DESCRIPTION, "description", UIConstants.TABLE_EX_LABEL_WIDTH)
    val environment: TableViewField = new TableViewField(BugI18nEnum.FORM_ENVIRONMENT, "environment", UIConstants.TABLE_EX_LABEL_WIDTH)
    val status: TableViewField = new TableViewField(BugI18nEnum.FORM_STATUS, "status", UIConstants.TABLE_M_LABEL_WIDTH)
    val severity: TableViewField = new TableViewField(BugI18nEnum.FORM_SEVERITY, "severity", UIConstants.TABLE_M_LABEL_WIDTH)
    val duedate: TableViewField = new TableViewField(BugI18nEnum.FORM_DUE_DATE, "duedate", UIConstants.TABLE_DATE_WIDTH)
    val logBy: TableViewField = new TableViewField(BugI18nEnum.FORM_LOG_BY, "loguserFullName", UIConstants.TABLE_X_LABEL_WIDTH)
    val priority: TableViewField = new TableViewField(BugI18nEnum.FORM_PRIORITY, "priority", UIConstants.TABLE_S_LABEL_WIDTH)
    val resolution: TableViewField = new TableViewField(BugI18nEnum.FORM_RESOLUTION, "resolution", UIConstants.TABLE_S_LABEL_WIDTH)
    val createdTime: TableViewField = new TableViewField(BugI18nEnum.FORM_CREATED_TIME, "createdtime", UIConstants.TABLE_DATE_TIME_WIDTH)
    val assignUser: TableViewField = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignuserFullName", UIConstants.TABLE_X_LABEL_WIDTH)
}
