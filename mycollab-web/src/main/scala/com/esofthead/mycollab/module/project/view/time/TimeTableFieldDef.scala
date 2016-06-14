package com.esofthead.mycollab.module.project.view.time

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.module.project.i18n.{ProjectI18nEnum, TimeTrackingI18nEnum}
import com.esofthead.mycollab.module.user.accountsettings.localization.UserI18nEnum
import com.esofthead.mycollab.vaadin.web.ui.UIConstants

/**
  * @author MyCollab Ltd
  * @since 5.2.8
  */
object TimeTableFieldDef {
  var id = new TableViewField(null, "id", 60)
  val summary = new TableViewField(TimeTrackingI18nEnum.FORM_SUMMARY, "summary", UIConstants.TABLE_X_LABEL_WIDTH)
  val logUser = new TableViewField(UserI18nEnum.SINGLE, "logUserFullName", UIConstants.TABLE_X_LABEL_WIDTH)
  val logValue = new TableViewField(TimeTrackingI18nEnum.LOG_VALUE, "logvalue", UIConstants.TABLE_S_LABEL_WIDTH)
  val billable = new TableViewField(TimeTrackingI18nEnum.FORM_IS_BILLABLE, "isbillable", UIConstants.TABLE_S_LABEL_WIDTH)
  val overtime = new TableViewField(TimeTrackingI18nEnum.FORM_IS_OVERTIME, "isovertime", UIConstants.TABLE_S_LABEL_WIDTH)
  val logForDate = new TableViewField(TimeTrackingI18nEnum.LOG_FOR_DATE, "logforday", UIConstants.TABLE_DATE_TIME_WIDTH)
  val project = new TableViewField(ProjectI18nEnum.SINGLE, "projectName", UIConstants.TABLE_X_LABEL_WIDTH)
}
