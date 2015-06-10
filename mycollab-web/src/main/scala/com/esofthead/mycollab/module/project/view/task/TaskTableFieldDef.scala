package com.esofthead.mycollab.module.project.view.task

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum
import com.esofthead.mycollab.vaadin.ui.UIConstants

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
object TaskTableFieldDef {
    val id: TableViewField = new TableViewField(null, "id", UIConstants.TABLE_CONTROL_WIDTH)
    val taskname: TableViewField = new TableViewField(TaskI18nEnum.FORM_TASK_NAME, "taskname", UIConstants.TABLE_X_LABEL_WIDTH)
    val startdate: TableViewField = new TableViewField(TaskI18nEnum.FORM_START_DATE, "startdate", UIConstants.TABLE_DATE_WIDTH)
    val duedate: TableViewField = new TableViewField(TaskI18nEnum.FORM_DEADLINE, "deadline", UIConstants.TABLE_DATE_WIDTH)
    val percentagecomplete: TableViewField = new TableViewField(TaskI18nEnum.FORM_PERCENTAGE_COMPLETE, "percentagecomplete", UIConstants.TABLE_S_LABEL_WIDTH)
    val assignee: TableViewField = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH)
}
