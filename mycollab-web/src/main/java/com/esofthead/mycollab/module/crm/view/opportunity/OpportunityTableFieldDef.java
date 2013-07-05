package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.localization.OpportunityI18nEnum;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.LocalizationHelper;

public interface OpportunityTableFieldDef {
	public static TableViewField selected = new TableViewField("", "selected",
			UIConstants.TABLE_CONTROL_WIDTH);

	public static TableViewField action = new TableViewField("", "id");

	public static TableViewField opportunityName = new TableViewField(
			LocalizationHelper.getMessage(OpportunityI18nEnum.FORM_NAME),
			"opportunityname", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField currency = new TableViewField(
			LocalizationHelper.getMessage(OpportunityI18nEnum.FORM_CURRENCY),
			"currency", UIConstants.TABLE_S_LABEL_WIDTH);

	public static TableViewField amount = new TableViewField(
			LocalizationHelper.getMessage(OpportunityI18nEnum.FORM_AMOUNT),
			"amount", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField probability = new TableViewField(
			LocalizationHelper.getMessage(OpportunityI18nEnum.FORM_PROBABILITY),
			"probability", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField accountName = new TableViewField(
			LocalizationHelper
					.getMessage(OpportunityI18nEnum.FORM_ACCOUNT_NAME),
			"accountName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField expectedCloseDate = new TableViewField(
			LocalizationHelper.getMessage(OpportunityI18nEnum.FORM_CLOSE_DATE),
			"expectedcloseddate", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField type = new TableViewField(
			LocalizationHelper.getMessage(OpportunityI18nEnum.FORM_TYPE),
			"type", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField leadSource = new TableViewField(
			LocalizationHelper.getMessage(OpportunityI18nEnum.FORM_TYPE),
			"source", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField campaignName = new TableViewField(
			LocalizationHelper
					.getMessage(OpportunityI18nEnum.FORM_CAMPAIGN_NAME),
			"campaignName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField assignUser = new TableViewField(
			LocalizationHelper.getMessage(OpportunityI18nEnum.FORM_ASSIGN_USER),
			"assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);
	
	public static TableViewField saleStage = new TableViewField(
			LocalizationHelper.getMessage(OpportunityI18nEnum.FORM_SALE_STAGE),
			"salesstage", UIConstants.TABLE_X_LABEL_WIDTH);
}
