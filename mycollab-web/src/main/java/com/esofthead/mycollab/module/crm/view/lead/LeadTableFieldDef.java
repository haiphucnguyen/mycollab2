package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.localization.LeadI18nEnum;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public interface LeadTableFieldDef {
	public static TableViewField selected = new TableViewField("", "selected",
			UIConstants.TABLE_CONTROL_WIDTH);

	public static TableViewField action = new TableViewField("", "id");

	public static TableViewField name = new TableViewField(
			LocalizationHelper.getMessage(LeadI18nEnum.FORM_NAME), "leadName",
			UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField title = new TableViewField(
			LocalizationHelper.getMessage(LeadI18nEnum.FORM_TITLE), "title",
			UIConstants.TABLE_S_LABEL_WIDTH);

	public static TableViewField department = new TableViewField(
			LocalizationHelper.getMessage(LeadI18nEnum.FORM_DEPARTMENT),
			"department", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField accountName = new TableViewField(
			LocalizationHelper.getMessage(LeadI18nEnum.FORM_ACCOUNT_NAME),
			"accountname", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField leadSource = new TableViewField(
			LocalizationHelper.getMessage(LeadI18nEnum.FORM_LEAD_SOURCE),
			"leadsourcedesc", UIConstants.TABLE_S_LABEL_WIDTH);

	public static TableViewField industry = new TableViewField(
			LocalizationHelper.getMessage(LeadI18nEnum.FORM_INDUSTRY),
			"industry", UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField email = new TableViewField(
			LocalizationHelper.getMessage(LeadI18nEnum.FORM_EMAIL), "email",
			UIConstants.TABLE_EMAIL_WIDTH);

	public static TableViewField phoneoffice = new TableViewField(
			LocalizationHelper
					.getMessage(CrmCommonI18nEnum.FORM_PHONE_OFFICE_FIELD),
			"officephone", UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField mobile = new TableViewField(
			LocalizationHelper.getMessage(LeadI18nEnum.FORM_MOBILE), "mobile",
			UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField fax = new TableViewField(
			LocalizationHelper.getMessage(LeadI18nEnum.FORM_FAX), "fax",
			UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField status = new TableViewField(
			LocalizationHelper.getMessage(LeadI18nEnum.FORM_STATUS), "status",
			UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField website = new TableViewField(
			LocalizationHelper.getMessage(LeadI18nEnum.FORM_WEBSITE),
			"website", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField assignedUser = new TableViewField(
			LocalizationHelper.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD),
			"assignUserFullName");

}
