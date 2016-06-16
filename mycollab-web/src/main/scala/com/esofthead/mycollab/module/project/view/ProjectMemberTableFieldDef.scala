package com.esofthead.mycollab.module.project.view

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.module.project.domain.{ProjectMember, SimpleProjectMember}
import com.esofthead.mycollab.module.project.i18n.{ProjectCommonI18nEnum, ProjectI18nEnum, ProjectMemberI18nEnum, TimeTrackingI18nEnum}
import com.esofthead.mycollab.vaadin.web.ui.UIConstants

/**
  * @author MyCollab Ltd
  * @since 5.2.12
  */
object ProjectMemberTableFieldDef {
  val memberName = new TableViewField(ProjectMemberI18nEnum.FORM_USER, ProjectMember.Field.username.name(), UIConstants.TABLE_X_LABEL_WIDTH)
  val billingRate = new TableViewField(ProjectI18nEnum.FORM_BILLING_RATE, ProjectMember.Field.billingrate.name(), UIConstants.TABLE_S_LABEL_WIDTH)
  val overtimeRate = new TableViewField(ProjectI18nEnum.FORM_OVERTIME_BILLING_RATE, ProjectMember.Field.overtimebillingrate.name(),
    UIConstants.TABLE_S_LABEL_WIDTH)
  val roleName = new TableViewField(ProjectMemberI18nEnum.FORM_ROLE, ProjectMember.Field.projectroleid.name(), UIConstants.TABLE_X_LABEL_WIDTH)
  val projectName = new TableViewField(ProjectI18nEnum.SINGLE, SimpleProjectMember.Field.projectName.name(), UIConstants.TABLE_X_LABEL_WIDTH)
  val totalBillableLogTime = new TableViewField(TimeTrackingI18nEnum.OPT_BILLABLE_HOURS, SimpleProjectMember.Field.totalBillableLogTime.name(),
    UIConstants.TABLE_X_LABEL_WIDTH)
  val totalNonBillableLogTime = new TableViewField(TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS, SimpleProjectMember.Field.totalNonBillableLogTime.name(),
    UIConstants.TABLE_X_LABEL_WIDTH)
  val numOpenTasks = new TableViewField(ProjectCommonI18nEnum.OPT_OPEN_TASKS, SimpleProjectMember.Field.numOpenTasks.name(),
    UIConstants.TABLE_X_LABEL_WIDTH)
  val numOpenBugs = new TableViewField(ProjectCommonI18nEnum.OPT_OPEN_BUGS, SimpleProjectMember.Field.numOpenBugs.name(),
    UIConstants.TABLE_X_LABEL_WIDTH)
}
