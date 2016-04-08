package com.esofthead.mycollab.module.project.view

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.module.project.domain.Project
import com.esofthead.mycollab.module.project.i18n.{ProjectI18nEnum, TaskI18nEnum}
import com.esofthead.mycollab.vaadin.web.ui.UIConstants

/**
  * @author MyCollab Ltd
  * @since 5.2.12
  */
object ProjectTableFieldDef {
  val selected = new TableViewField(null, "selected", UIConstants.TABLE_CONTROL_WIDTH);
  val projectname = new TableViewField(ProjectI18nEnum.FORM_NAME, Project.Field.name.name(), UIConstants.TABLE_X_LABEL_WIDTH)
}
