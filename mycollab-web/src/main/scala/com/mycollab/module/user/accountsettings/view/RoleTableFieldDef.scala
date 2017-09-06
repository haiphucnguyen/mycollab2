package com.mycollab.module.user.accountsettings.view

import com.mycollab.common.TableViewField
import com.mycollab.common.i18n.GenericI18Enum
import com.mycollab.module.user.accountsettings.localization.RoleI18nEnum
import com.mycollab.vaadin.web.ui.WebUIConstants

/**
  * @author MyCollab Ltd
  * @since 5.2.12
  */
object RoleTableFieldDef {
  val selected = new TableViewField(null, "selected", WebUIConstants.TABLE_CONTROL_WIDTH)
  val rolename = new TableViewField(GenericI18Enum.FORM_NAME, "rolename", WebUIConstants.TABLE_EX_LABEL_WIDTH)
  val isDefault = new TableViewField(RoleI18nEnum.FORM_IS_DEFAULT, "isdefault", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val description = new TableViewField(GenericI18Enum.FORM_DESCRIPTION, "description", WebUIConstants.TABLE_EX_LABEL_WIDTH)
}
