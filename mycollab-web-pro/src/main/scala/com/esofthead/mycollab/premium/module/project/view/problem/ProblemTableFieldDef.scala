package com.esofthead.mycollab.premium.module.project.view.problem

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.module.project.i18n.ProblemI18nEnum
import com.esofthead.mycollab.vaadin.ui.UIConstants

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
object ProblemTableFieldDef {
  val selected: TableViewField = new TableViewField(null, "selected", UIConstants.TABLE_CONTROL_WIDTH)
  val action: TableViewField = new TableViewField(null, "id", -1)
  val name: TableViewField = new TableViewField(ProblemI18nEnum.FORM_NAME, "issuename", UIConstants.TABLE_X_LABEL_WIDTH)
  val description: TableViewField = new TableViewField(GenericI18Enum.FORM_DESCRIPTION, "description", UIConstants.TABLE_EX_LABEL_WIDTH)
  val raisedby: TableViewField = new TableViewField(ProblemI18nEnum.FORM_RAISED_BY, "raisedByUserFullName", UIConstants.TABLE_X_LABEL_WIDTH)
  val assignUser: TableViewField = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignedUserFullName", UIConstants.TABLE_X_LABEL_WIDTH)
  val datedue: TableViewField = new TableViewField(ProblemI18nEnum.FORM_DATE_DUE, "datedue", UIConstants.TABLE_DATE_WIDTH)
  val status: TableViewField = new TableViewField(ProblemI18nEnum.FORM_STATUS, "status", UIConstants.TABLE_S_LABEL_WIDTH)
  val impact: TableViewField = new TableViewField(ProblemI18nEnum.FORM_IMPACT, "impact", UIConstants.TABLE_S_LABEL_WIDTH)
  val priority: TableViewField = new TableViewField(ProblemI18nEnum.FORM_PRIORITY, "priority", UIConstants.TABLE_S_LABEL_WIDTH)
  val rating: TableViewField = new TableViewField(ProblemI18nEnum.FORM_RATING, "level", UIConstants.TABLE_M_LABEL_WIDTH)
}
