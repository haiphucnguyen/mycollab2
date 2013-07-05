package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.localization.ContactI18nEnum;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.LocalizationHelper;

public interface ContactTableFieldDef {
	public static TableViewField selected = new TableViewField("", "selected",
			UIConstants.TABLE_CONTROL_WIDTH);

	public static TableViewField action = new TableViewField("", "id");

	public static TableViewField name = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_NAME),
			"contactName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField account = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_ACCOUNTS),
			"accountName", UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField title = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_TITLE), "title",
			UIConstants.TABLE_S_LABEL_WIDTH);

	public static TableViewField department = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_DEPARTMENT),
			"department", UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField email = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_EMAIL), "email",
			UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField assistant = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_ASSISTANT),
			"assistant", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField assistantPhone = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_ASSISTANT),
			"assistantphone", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField phoneOffice = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_PHONE_OFFICE),
			"officephone", UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField mobile = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_MOBILE),
			"mobile", UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField fax = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_FAX), "fax",
			UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField birthday = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_BIRTHDAY),
			"birthday", UIConstants.TABLE_DATE_WIDTH);

	public static TableViewField isCallable = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_IS_CALLABLE),
			"iscallable", UIConstants.TABLE_S_LABEL_WIDTH);

	public static TableViewField assignUser = new TableViewField(
			LocalizationHelper.getMessage(ContactI18nEnum.FORM_ASSIGN_USER),
			"assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

}
