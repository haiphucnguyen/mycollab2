package com.esofthead.mycollab.module.crm.view.account

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.module.crm.i18n.AccountI18nEnum
import com.esofthead.mycollab.vaadin.web.ui.UIConstants

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
object AccountTableFieldDef {
  val selected = new TableViewField(null, "selected", UIConstants.TABLE_CONTROL_WIDTH)
  val action = new TableViewField(null, "id", UIConstants.TABLE_ACTION_CONTROL_WIDTH)
  val accountname = new TableViewField(AccountI18nEnum.FORM_ACCOUNT_NAME, "accountname", UIConstants.TABLE_X_LABEL_WIDTH)
  val city = new TableViewField(AccountI18nEnum.FORM_BILLING_CITY, "city", UIConstants.TABLE_X_LABEL_WIDTH)
  val phoneoffice = new TableViewField(AccountI18nEnum.FORM_OFFICE_PHONE, "phoneoffice", UIConstants.TABLE_M_LABEL_WIDTH)
  val email = new TableViewField(AccountI18nEnum.FORM_EMAIL, "email", UIConstants.TABLE_EMAIL_WIDTH)
  val assignUser = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH)
  val website = new TableViewField(AccountI18nEnum.FORM_WEBSITE, "website", UIConstants.TABLE_X_LABEL_WIDTH)
  val `type` = new TableViewField(AccountI18nEnum.FORM_TYPE, "type", UIConstants.TABLE_X_LABEL_WIDTH)
  val ownership = new TableViewField(AccountI18nEnum.FORM_OWNERSHIP, "ownership", UIConstants.TABLE_X_LABEL_WIDTH)
  val fax = new TableViewField(AccountI18nEnum.FORM_FAX, "fax", UIConstants.TABLE_M_LABEL_WIDTH)
}
