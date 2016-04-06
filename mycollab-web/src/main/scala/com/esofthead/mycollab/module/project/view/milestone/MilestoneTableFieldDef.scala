package com.esofthead.mycollab.module.project.view.milestone

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.module.project.domain.{Milestone, SimpleMilestone}
import com.esofthead.mycollab.module.project.i18n.MilestoneI18nEnum
import com.esofthead.mycollab.vaadin.web.ui.UIConstants

/**
  * @author MyCollab Ltd
  * @since 5.2.11
  */
object MilestoneTableFieldDef {
  val milestonename = new TableViewField(MilestoneI18nEnum.FORM_NAME_FIELD, Milestone.Field.name.name(), UIConstants.TABLE_X_LABEL_WIDTH)

  val status = new TableViewField(MilestoneI18nEnum.FORM_STATUS_FIELD, Milestone.Field.status.name(), UIConstants
    .TABLE_S_LABEL_WIDTH)

  val startdate = new TableViewField(MilestoneI18nEnum.FORM_START_DATE_FIELD, Milestone.Field.startdate.name(), UIConstants
    .TABLE_DATE_WIDTH)

  val enddate = new TableViewField(MilestoneI18nEnum.FORM_END_DATE_FIELD, Milestone.Field.enddate.name(), UIConstants
    .TABLE_DATE_WIDTH)

  val assignee = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, SimpleMilestone.Field.ownerFullName.name(), UIConstants
    .TABLE_M_LABEL_WIDTH)
}
