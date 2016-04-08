package com.esofthead.mycollab.module.project.view

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.module.project.domain.ProjectMember
import com.esofthead.mycollab.module.project.i18n.{ProjectI18nEnum, ProjectMemberI18nEnum}
import com.esofthead.mycollab.vaadin.web.ui.UIConstants

/**
  * @author MyCollab Ltd
  * @since 5.2.12
  */
object ProjectMemberTableFieldDef {
  val membername = new TableViewField(ProjectMemberI18nEnum.FORM_USER, ProjectMember.Field.username.name(), UIConstants.TABLE_X_LABEL_WIDTH)
  val billingRate = new TableViewField(ProjectI18nEnum.FORM_BILLING_RATE, ProjectMember.Field.billingrate.name(), UIConstants.TABLE_S_LABEL_WIDTH)
  val overtimeRate = new TableViewField(ProjectI18nEnum.FORM_OVERTIME_BILLING_RATE, ProjectMember.Field.overtimebillingrate.name(),
    UIConstants.TABLE_S_LABEL_WIDTH)
  val rolename = new TableViewField(ProjectMemberI18nEnum.FORM_ROLE, ProjectMember.Field.projectroleid.name(), UIConstants.TABLE_X_LABEL_WIDTH)
}
