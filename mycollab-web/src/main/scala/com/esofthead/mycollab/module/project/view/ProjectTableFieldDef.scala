package com.esofthead.mycollab.module.project.view

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.module.project.domain.Project
import com.esofthead.mycollab.module.project.i18n.ProjectI18nEnum
import com.esofthead.mycollab.vaadin.web.ui.UIConstants

/**
  * @author MyCollab Ltd
  * @since 5.2.12
  */
object ProjectTableFieldDef {
  val selected = new TableViewField(null, "selected", UIConstants.TABLE_CONTROL_WIDTH);
  val projectname = new TableViewField(ProjectI18nEnum.FORM_NAME, Project.Field.name.name(), UIConstants.TABLE_X_LABEL_WIDTH)
  val lead = new TableViewField(ProjectI18nEnum.FORM_LEADER, Project.Field.lead.name(), UIConstants.TABLE_EX_LABEL_WIDTH)
  val client = new TableViewField(ProjectI18nEnum.FORM_ACCOUNT_NAME, Project.Field.accountid.name(), UIConstants.TABLE_EX_LABEL_WIDTH)
  val createdDate = new TableViewField(GenericI18Enum.FORM_CREATED_TIME, Project.Field.createdtime.name(), UIConstants.TABLE_DATE_WIDTH)
  val homePage = new TableViewField(ProjectI18nEnum.FORM_HOME_PAGE, Project.Field.homepage.name(), UIConstants.TABLE_EX_LABEL_WIDTH)
  val status = new TableViewField(ProjectI18nEnum.FORM_STATUS, Project.Field.projectstatus.name(), UIConstants.TABLE_S_LABEL_WIDTH)
  val startDate = new TableViewField(ProjectI18nEnum.FORM_START_DATE, Project.Field.planstartdate.name(), UIConstants.TABLE_DATE_WIDTH)
  val endDate = new TableViewField(ProjectI18nEnum.FORM_END_DATE, Project.Field.planenddate.name(), UIConstants.TABLE_DATE_WIDTH)
  val rate = new TableViewField(ProjectI18nEnum.FORM_BILLING_RATE, Project.Field.defaultbillingrate.name(), UIConstants.TABLE_M_LABEL_WIDTH)
  val overtimerate = new TableViewField(ProjectI18nEnum.FORM_OVERTIME_BILLING_RATE, Project.Field.defaultovertimebillingrate.name(), UIConstants.TABLE_M_LABEL_WIDTH)
  val budget = new TableViewField(ProjectI18nEnum.FORM_TARGET_BUDGET, Project.Field.targetbudget.name(), UIConstants.TABLE_M_LABEL_WIDTH)
  val actualBudget = new TableViewField(ProjectI18nEnum.FORM_ACTUAL_BUDGET, Project.Field.targetbudget.name(), UIConstants
    .TABLE_M_LABEL_WIDTH)
}
