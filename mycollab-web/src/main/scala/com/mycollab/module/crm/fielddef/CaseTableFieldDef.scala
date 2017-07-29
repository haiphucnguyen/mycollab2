package com.mycollab.module.crm.fielddef

import com.mycollab.common.TableViewField
import com.mycollab.common.i18n.GenericI18Enum
import com.mycollab.module.crm.i18n.CaseI18nEnum
import com.mycollab.vaadin.web.ui.WebUIConstants

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
object CaseTableFieldDef {
  val selected = new TableViewField(null, "selected", WebUIConstants.TABLE_CONTROL_WIDTH)
  val action = new TableViewField(null, "id", -1)
  val priority = new TableViewField(CaseI18nEnum.FORM_PRIORITY, "priority", WebUIConstants.TABLE_S_LABEL_WIDTH)
  val status = new TableViewField(GenericI18Enum.FORM_STATUS, "status", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val account = new TableViewField(CaseI18nEnum.FORM_ACCOUNT, "accountName", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val origin = new TableViewField(CaseI18nEnum.FORM_ORIGIN, "origin", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val phone = new TableViewField(GenericI18Enum.FORM_PHONE, "phonenumber", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val `type` = new TableViewField(GenericI18Enum.FORM_TYPE, "type", WebUIConstants.TABLE_M_LABEL_WIDTH)
  val reason = new TableViewField(CaseI18nEnum.FORM_REASON, "reason", WebUIConstants.TABLE_EX_LABEL_WIDTH)
  val subject = new TableViewField(CaseI18nEnum.FORM_SUBJECT, "subject", WebUIConstants.TABLE_EX_LABEL_WIDTH)
  val email = new TableViewField(GenericI18Enum.FORM_EMAIL, "email", WebUIConstants.TABLE_EMAIL_WIDTH)
  val assignUser = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignUserFullName", WebUIConstants.TABLE_X_LABEL_WIDTH)
  val createdTime = new TableViewField(GenericI18Enum.FORM_CREATED_TIME, "createdtime", WebUIConstants.TABLE_DATE_TIME_WIDTH)
  val lastUpdatedTime = new TableViewField(GenericI18Enum.FORM_LAST_UPDATED_TIME, "lastupdatedtime", WebUIConstants.TABLE_DATE_TIME_WIDTH)
}
