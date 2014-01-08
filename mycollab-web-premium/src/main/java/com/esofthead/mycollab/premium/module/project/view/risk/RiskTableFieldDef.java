package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.localization.RiskI18nEnum;
import com.esofthead.mycollab.vaadin.resource.ui.UIConstants;
import com.esofthead.mycollab.vaadin.resource.ui.table.TableViewField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public interface RiskTableFieldDef {
	public static TableViewField selected = new TableViewField("", "selected",
			UIConstants.TABLE_CONTROL_WIDTH);

	public static TableViewField action = new TableViewField("", "id");

	public static TableViewField name = new TableViewField(
			LocalizationHelper.getMessage(RiskI18nEnum.FORM_NAME), "riskname",
			UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField description = new TableViewField(
			LocalizationHelper.getMessage(RiskI18nEnum.FORM_DESCRIPTION),
			"description", UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField raisedBy = new TableViewField(
			LocalizationHelper.getMessage(RiskI18nEnum.FORM_RAISED_BY),
			"raisedByUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField assignUser = new TableViewField(
			LocalizationHelper.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD),
			"assignedToUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField datedue = new TableViewField(
			LocalizationHelper.getMessage(RiskI18nEnum.FORM_DATE_DUE),
			"datedue", UIConstants.TABLE_DATE_WIDTH);

	public static TableViewField status = new TableViewField(
			LocalizationHelper.getMessage(RiskI18nEnum.FORM_STATUS), "status",
			UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField response = new TableViewField(
			LocalizationHelper.getMessage(RiskI18nEnum.FORM_RESPONSE),
			"response", UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField consequence = new TableViewField(
			LocalizationHelper.getMessage(RiskI18nEnum.FORM_CONSEQUENCE),
			"consequence", UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField probability = new TableViewField(
			LocalizationHelper.getMessage(RiskI18nEnum.FORM_PROBABILITY),
			"probalitity", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField rating = new TableViewField(
			LocalizationHelper.getMessage(RiskI18nEnum.FORM_RATING), "level",
			UIConstants.TABLE_M_LABEL_WIDTH);
}
