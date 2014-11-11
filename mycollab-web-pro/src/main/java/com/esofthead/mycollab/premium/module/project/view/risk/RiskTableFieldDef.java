package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum;
import com.esofthead.mycollab.vaadin.ui.UIConstants;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public interface RiskTableFieldDef {
	public static TableViewField selected = new TableViewField(null,
			"selected", UIConstants.TABLE_CONTROL_WIDTH);

	public static TableViewField action = new TableViewField(null, "id");

	public static TableViewField name = new TableViewField(
			RiskI18nEnum.FORM_NAME, "riskname",
			UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField description = new TableViewField(
			RiskI18nEnum.FORM_DESCRIPTION, "description",
			UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField raisedBy = new TableViewField(
			RiskI18nEnum.FORM_RAISED_BY, "raisedByUserFullName",
			UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField assignUser = new TableViewField(
			GenericI18Enum.FORM_ASSIGNEE, "assignedToUserFullName",
			UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField datedue = new TableViewField(
			RiskI18nEnum.FORM_DATE_DUE, "datedue", UIConstants.TABLE_DATE_WIDTH);

	public static TableViewField status = new TableViewField(
			RiskI18nEnum.FORM_STATUS, "status", UIConstants.TABLE_M_LABEL_WIDTH);

	public static TableViewField response = new TableViewField(
			RiskI18nEnum.FORM_RESPONSE, "response",
			UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField consequence = new TableViewField(
			RiskI18nEnum.FORM_CONSEQUENCE, "consequence",
			UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField probability = new TableViewField(
			RiskI18nEnum.FORM_PROBABILITY, "probalitity",
			UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField rating = new TableViewField(
			RiskI18nEnum.FORM_RATING, "level", UIConstants.TABLE_M_LABEL_WIDTH);
}
