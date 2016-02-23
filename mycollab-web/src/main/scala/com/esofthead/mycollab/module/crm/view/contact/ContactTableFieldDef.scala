package com.esofthead.mycollab.module.crm.view.contact

import com.esofthead.mycollab.common.TableViewField
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.module.crm.i18n.ContactI18nEnum
import com.esofthead.mycollab.vaadin.web.ui.UIConstants

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
object ContactTableFieldDef {
    val selected = new TableViewField(null, "selected", UIConstants.TABLE_CONTROL_WIDTH)
    val action = new TableViewField(null, "id", UIConstants.TABLE_ACTION_CONTROL_WIDTH)
    val name = new TableViewField(ContactI18nEnum.FORM_NAME, "contactName", UIConstants.TABLE_X_LABEL_WIDTH)
    val account = new TableViewField(ContactI18nEnum.FORM_ACCOUNTS, "accountName", UIConstants.TABLE_EX_LABEL_WIDTH)
    val decisionRole = new TableViewField(ContactI18nEnum.FORM_DECISION_ROLE, "decisionRole", UIConstants.TABLE_M_LABEL_WIDTH)
    val title = new TableViewField(ContactI18nEnum.FORM_TITLE, "title", UIConstants.TABLE_S_LABEL_WIDTH)
    val department = new TableViewField(ContactI18nEnum.FORM_DEPARTMENT, "department", UIConstants.TABLE_M_LABEL_WIDTH)
    val email = new TableViewField(ContactI18nEnum.FORM_EMAIL, "email", UIConstants.TABLE_EMAIL_WIDTH)
    val assistant = new TableViewField(ContactI18nEnum.FORM_ASSISTANT, "assistant", UIConstants.TABLE_X_LABEL_WIDTH)
    val assistantPhone = new TableViewField(ContactI18nEnum.FORM_ASSISTANT_PHONE, "assistantphone", UIConstants.TABLE_X_LABEL_WIDTH)
    val phoneOffice = new TableViewField(ContactI18nEnum.FORM_OFFICE_PHONE, "officephone", UIConstants.TABLE_M_LABEL_WIDTH)
    val mobile = new TableViewField(ContactI18nEnum.FORM_MOBILE, "mobile", UIConstants.TABLE_M_LABEL_WIDTH)
    val fax = new TableViewField(ContactI18nEnum.FORM_FAX, "fax", UIConstants.TABLE_M_LABEL_WIDTH)
    val birthday = new TableViewField(ContactI18nEnum.FORM_BIRTHDAY, "birthday", UIConstants.TABLE_DATE_WIDTH)
    val isCallable = new TableViewField(ContactI18nEnum.FORM_IS_CALLABLE, "iscallable", UIConstants.TABLE_S_LABEL_WIDTH)
    val assignUser = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH)
}
