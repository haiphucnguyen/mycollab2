package com.mycollab.module.project.fielddef

import com.mycollab.common.TableViewField
import com.mycollab.module.project.i18n.{ProjectI18nEnum, TimeTrackingI18nEnum}
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum
import com.mycollab.vaadin.web.ui.WebUIConstants

/**
  * @author MyCollab Ltd
  * @since 5.2.8
  */
object TimeTableFieldDef {
  var id = new TableViewField(null, "id", 60)
  val summary = new TableViewField(TimeTrackingI18nEnum.FORM_SUMMARY, "name", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val logUser = new TableViewField(UserI18nEnum.SINGLE, "logUserFullName", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val logValue = new TableViewField(TimeTrackingI18nEnum.LOG_VALUE, "logvalue", WebUIConstants.TABLE_S_LABEL_WIDTH)
  val billable = new TableViewField(TimeTrackingI18nEnum.FORM_IS_BILLABLE, "isbillable", WebUIConstants.TABLE_S_LABEL_WIDTH)
  val overtime = new TableViewField(TimeTrackingI18nEnum.FORM_IS_OVERTIME, "isovertime", WebUIConstants.TABLE_S_LABEL_WIDTH)
  val logForDate = new TableViewField(TimeTrackingI18nEnum.LOG_FOR_DATE, "logforday", WebUIConstants.TABLE_DATE_TIME_WIDTH)
  val project = new TableViewField(ProjectI18nEnum.SINGLE, "projectName", WebUIConstants.TABLE_X_LABEL_WIDTH)
}
