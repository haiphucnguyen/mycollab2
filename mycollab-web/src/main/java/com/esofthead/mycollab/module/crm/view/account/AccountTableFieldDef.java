package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.localization.AccountI18nEnum;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.LocalizationHelper;

public interface AccountTableFieldDef {

	public static TableViewField selected = new TableViewField("", "selected",
			UIConstants.TABLE_CONTROL_WIDTH);

	public static TableViewField action = new TableViewField("", "id");

	public static TableViewField accountname = new TableViewField(
			LocalizationHelper.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
			"accountname", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField city = new TableViewField(
			LocalizationHelper.getMessage(CrmCommonI18nEnum.TABLE_CITY_HEADER),
			"city", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField phoneoffice = new TableViewField(
			LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TABLE_OFFICE_PHONE_HEADER),
			"phoneoffice", UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField email = new TableViewField(
			LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TABLE_EMAIL_ADDRESS_HEADER),
			"email", UIConstants.TABLE_EMAIL_WIDTH);

	public static TableViewField assignUser = new TableViewField(
			LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TABLE_ASSIGNED_USER_HEADER),
			"assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField website = new TableViewField(
			LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TABLE_WEBSITE_HEADER),
			"website", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField type = new TableViewField(
			LocalizationHelper.getMessage(AccountI18nEnum.FORM_TYPE), "type",
			UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField ownership = new TableViewField(
			LocalizationHelper.getMessage(AccountI18nEnum.FORM_OWNERSHIP),
			"ownership", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField fax = new TableViewField(
			LocalizationHelper.getMessage(AccountI18nEnum.FORM_FAX), "fax",
			UIConstants.TABLE_M_LABEL_WIDTH);

}
