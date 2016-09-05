package com.mycollab.module.user.accountsettings.view

import com.mycollab.common.TableViewField
import com.mycollab.common.i18n.GenericI18Enum
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum
import com.mycollab.module.user.domain.{SimpleUser, User}
import com.mycollab.vaadin.web.ui.WebUIConstants

/**
  * @author MyCollab Ltd
  * @since 5.2.12
  */
object UserTableFieldDef {
  val displayName = new TableViewField(GenericI18Enum.FORM_NAME, SimpleUser.Field.displayName.name(), WebUIConstants.TABLE_EX_LABEL_WIDTH)
  val roleName = new TableViewField(UserI18nEnum.FORM_ROLE, SimpleUser.Field.roleid.name(), WebUIConstants.TABLE_EX_LABEL_WIDTH)
  val email = new TableViewField(GenericI18Enum.FORM_EMAIL, User.Field.email.name(), WebUIConstants.TABLE_X_LABEL_WIDTH)
  val birthday = new TableViewField(UserI18nEnum.FORM_BIRTHDAY, User.Field.dateofbirth.name(), WebUIConstants.TABLE_DATE_WIDTH)
  val officePhone = new TableViewField(UserI18nEnum.FORM_WORK_PHONE, User.Field.workphone.name(), WebUIConstants.TABLE_M_LABEL_WIDTH)
  val homePhone = new TableViewField(UserI18nEnum.FORM_HOME_PHONE, User.Field.homephone.name(), WebUIConstants.TABLE_M_LABEL_WIDTH)
  val company = new TableViewField(UserI18nEnum.FORM_COMPANY, User.Field.company.name(), WebUIConstants.TABLE_M_LABEL_WIDTH)
}
