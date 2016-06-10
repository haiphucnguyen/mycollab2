package com.esofthead.mycollab.module.project.view.bug

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.module.project.i18n.{BugI18nEnum, TimeTrackingI18nEnum}
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs
import com.esofthead.mycollab.vaadin.web.ui.UIConstants

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
object BugTableFieldDef {
  val action = new TableViewField(null, "id", UIConstants.TABLE_CONTROL_WIDTH)
  val summary = new TableViewField(BugI18nEnum.FORM_SUMMARY, "summary", UIConstants.TABLE_EX_LABEL_WIDTH)
  val description = new TableViewField(GenericI18Enum.FORM_DESCRIPTION, "description", UIConstants.TABLE_EX_LABEL_WIDTH)
  val environment = new TableViewField(BugI18nEnum.FORM_ENVIRONMENT, "environment", UIConstants.TABLE_EX_LABEL_WIDTH)
  val status = new TableViewField(GenericI18Enum.FORM_STATUS, "status", UIConstants.TABLE_M_LABEL_WIDTH)
  val severity = new TableViewField(BugI18nEnum.FORM_SEVERITY, "severity", UIConstants.TABLE_M_LABEL_WIDTH)
  val startDate = new TableViewField(GenericI18Enum.FORM_START_DATE, BugWithBLOBs.Field.startdate.name(), UIConstants.TABLE_DATE_WIDTH)
  val endDate = new TableViewField(GenericI18Enum.FORM_END_DATE, BugWithBLOBs.Field.enddate.name(), UIConstants.TABLE_DATE_WIDTH)
  val dueDate = new TableViewField(GenericI18Enum.FORM_DUE_DATE, "duedate", UIConstants.TABLE_DATE_WIDTH)
  val logBy = new TableViewField(BugI18nEnum.FORM_LOG_BY, "loguserFullName", UIConstants.TABLE_X_LABEL_WIDTH)
  val priority = new TableViewField(BugI18nEnum.FORM_PRIORITY, "priority", UIConstants.TABLE_S_LABEL_WIDTH)
  val resolution = new TableViewField(BugI18nEnum.FORM_RESOLUTION, "resolution", UIConstants.TABLE_M_LABEL_WIDTH)
  val createdTime = new TableViewField(GenericI18Enum.FORM_CREATED_TIME, "createdtime", UIConstants.TABLE_DATE_TIME_WIDTH)
  val assignUser = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignuserFullName", UIConstants.TABLE_X_LABEL_WIDTH)
  val milestoneName = new TableViewField(BugI18nEnum.FORM_PHASE, "milestoneName", UIConstants.TABLE_X_LABEL_WIDTH)
  val billableHours = new TableViewField(TimeTrackingI18nEnum.OPT_BILLABLE_HOURS, "billableHours", UIConstants.TABLE_M_LABEL_WIDTH)
  val nonBillableHours = new TableViewField(TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS, "nonBillableHours", UIConstants.TABLE_M_LABEL_WIDTH)
}
