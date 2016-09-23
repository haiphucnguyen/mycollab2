package com.mycollab.module.project.view.task

import com.mycollab.common.TableViewField
import com.mycollab.common.i18n.GenericI18Enum
import com.mycollab.module.project.domain.Task
import com.mycollab.module.project.i18n.{TaskI18nEnum, TimeTrackingI18nEnum}
import com.mycollab.vaadin.web.ui.WebUIConstants

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
object TaskTableFieldDef {
  val id = new TableViewField(null, "id", WebUIConstants.TABLE_CONTROL_WIDTH)
  val taskname = new TableViewField(GenericI18Enum.FORM_NAME, Task.Field.name.name(), WebUIConstants.TABLE_X_LABEL_WIDTH)
  val note = new TableViewField(TaskI18nEnum.FORM_NOTES, Task.Field.description.name(), WebUIConstants.TABLE_EX_LABEL_WIDTH)
  val originalEstimate = new TableViewField(TaskI18nEnum.FORM_ORIGINAL_ESTIMATE, "originalestimate", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val remainEstimate = new TableViewField(TaskI18nEnum.FORM_REMAIN_ESTIMATE, "remainestimate", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val isEstimate = new TableViewField(TaskI18nEnum.FORM_IS_ESTIMATED, "isestimated", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val status = new TableViewField(GenericI18Enum.FORM_STATUS, "status", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val priority = new TableViewField(GenericI18Enum.FORM_PRIORITY, "priority", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val startdate = new TableViewField(GenericI18Enum.FORM_START_DATE, Task.Field.startdate.name(), WebUIConstants.TABLE_DATE_WIDTH)
  val enddate = new TableViewField(GenericI18Enum.FORM_END_DATE, Task.Field.enddate.name(), WebUIConstants.TABLE_DATE_WIDTH)
  val duedate = new TableViewField(GenericI18Enum.FORM_DUE_DATE, Task.Field.duedate.name(), WebUIConstants.TABLE_DATE_WIDTH)
  val percentagecomplete = new TableViewField(TaskI18nEnum.FORM_PERCENTAGE_COMPLETE, "percentagecomplete", WebUIConstants.TABLE_S_LABEL_WIDTH)
  val logUser = new TableViewField(TaskI18nEnum.FORM_LOG_BY, "logByFullName", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val assignee = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignUserFullName", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val milestoneName = new TableViewField(TaskI18nEnum.FORM_PHASE, "milestoneName", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val billableHours = new TableViewField(TimeTrackingI18nEnum.OPT_BILLABLE_HOURS, "billableHours", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val nonBillableHours = new TableViewField(TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS, "nonBillableHours", WebUIConstants.TABLE_M_LABEL_WIDTH)
}
