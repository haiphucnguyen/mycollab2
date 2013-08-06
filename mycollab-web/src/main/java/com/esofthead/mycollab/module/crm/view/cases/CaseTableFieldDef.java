package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.localization.CaseI18nEnum;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public interface CaseTableFieldDef {
	public static TableViewField selected = new TableViewField("", "selected",
			UIConstants.TABLE_CONTROL_WIDTH);

	public static TableViewField action = new TableViewField("", "id");

	public static TableViewField priority = new TableViewField(
			LocalizationHelper.getMessage(CaseI18nEnum.FORM_PRIORITY),
			"priority", UIConstants.TABLE_S_LABEL_WIDTH);

	public static TableViewField status = new TableViewField(
			LocalizationHelper.getMessage(CaseI18nEnum.FORM_STATUS), "status",
			UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField account = new TableViewField(
			LocalizationHelper.getMessage(CaseI18nEnum.FORM_ACCOUNT),
			"accountName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField origin = new TableViewField(
			LocalizationHelper.getMessage(CaseI18nEnum.FORM_ORIGIN), "origin",
			UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField phone = new TableViewField(
			LocalizationHelper.getMessage(CaseI18nEnum.FORM_PHONE),
			"phonenumber", UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField type = new TableViewField(
			LocalizationHelper.getMessage(CaseI18nEnum.FORM_TYPE), "type",
			UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField reason = new TableViewField(
			LocalizationHelper.getMessage(CaseI18nEnum.FORM_REASON), "reason",
			UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField subject = new TableViewField(
			LocalizationHelper.getMessage(CaseI18nEnum.FORM_SUBJECT),
			"subject", UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField email = new TableViewField(
			LocalizationHelper.getMessage(CaseI18nEnum.FORM_EMAIL), "email",
			UIConstants.TABLE_EMAIL_WIDTH);

	public static TableViewField assignUser = new TableViewField(
			LocalizationHelper.getMessage(CaseI18nEnum.FORM_ASSIGN_USER),
			"assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField createdTime = new TableViewField(
			LocalizationHelper.getMessage(CaseI18nEnum.FORM_CREATED_TIME),
			"createdtime", UIConstants.TABLE_DATE_TIME_WIDTH);

	public static TableViewField lastUpdatedTime = new TableViewField(
			LocalizationHelper.getMessage(CaseI18nEnum.FORM_LAST_UPDATED_TIME),
			"lastupdatedtime", UIConstants.TABLE_DATE_TIME_WIDTH);
}
