package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.localization.CampaignI18nEnum;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.LocalizationHelper;

public interface CampaignTableFieldDef {
	public static TableViewField selected = new TableViewField("", "selected",
			UIConstants.TABLE_CONTROL_WIDTH);

	public static TableViewField action = new TableViewField("", "id");

	public static TableViewField campaignname = new TableViewField(
			LocalizationHelper.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
			"campaignname", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField status = new TableViewField(
			LocalizationHelper.getMessage(CampaignI18nEnum.FORM_STATUS),
			"status", UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField type = new TableViewField(
			LocalizationHelper.getMessage(CampaignI18nEnum.FORM_TYPE), "type",
			UIConstants.TABLE_S_LABEL_WIDTH);

	public static TableViewField expectedRevenue = new TableViewField(
			LocalizationHelper
					.getMessage(CampaignI18nEnum.FORM_EXPECTED_REVENUE),
			"expectedrevenue", UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField endDate = new TableViewField(
			LocalizationHelper.getMessage(CampaignI18nEnum.FORM_END_DATE),
			"enddate", UIConstants.TABLE_DATE_WIDTH);

	public static TableViewField startDate = new TableViewField(
			LocalizationHelper.getMessage(CampaignI18nEnum.FORM_START_DATE),
			"startdate", UIConstants.TABLE_DATE_WIDTH);

	public static TableViewField assignUser = new TableViewField(
			LocalizationHelper.getMessage(CampaignI18nEnum.FORM_ASSIGN_USER),
			"assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);
}
